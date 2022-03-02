package io.saikat.spring.elasticsearch.equipmentapp.controller;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import io.saikat.spring.elasticsearch.equipmentapp.exception.EquipmentAlreadyIndexException;
import io.saikat.spring.elasticsearch.equipmentapp.exception.InvalidRequestParamException;
import io.saikat.spring.elasticsearch.equipmentapp.service.EquipmentService;
import lombok.val;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EquipmentController.class)
public class EquipmentControllerTest {

  @Autowired
  private WebTestClient webClient;

  @MockBean
  private EquipmentService equipmentService;

  @Test
  public void whenInvalidLimitInput() throws Exception {
    doThrow(new InvalidRequestParamException("Empty")).when(equipmentService).fetchEquipments("Test");
    webClient.get().uri("/equipment-service/v1/equipment/search/{limit}", "Test")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus().isBadRequest();
  }

  @Test
  public void fetchEquipment() throws Exception {
    val equipments = Flux.just(Equipment.builder().id("111").build());
    given(equipmentService.fetchEquipments("10")).willReturn(equipments);
    webClient.get().uri("/equipment-service/v1/equipment/search/{limit}", "10")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Equipment.class)
        .contains(equipments.blockFirst());
  }

  @Test
  public void createEquipment() {

    val equipment = Equipment.builder().id("111").build();
    given(equipmentService.indexEquipment(equipment)).willReturn(Mono.just(equipment));
    webClient.post()
        .uri("/equipment-service/v1/equipment/index")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(equipment))
        .exchange()
        .expectStatus().isCreated();
    verify(equipmentService, times(1)).indexEquipment(equipment);
  }

  @Test
  public void createFailsDuplicateEquipment() {
    val equipment = Equipment.builder().id("111").build();
    doThrow(new EquipmentAlreadyIndexException("Already there")).when(equipmentService).indexEquipment(equipment);
    webClient.post()
        .uri("/equipment-service/v1/equipment/index")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(equipment))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  public void searchEquipment() throws Exception {
    val equipment = Equipment.builder().id("10").build();
    given(equipmentService.fetchEquipmentById("10")).willReturn(Mono.just(equipment));
    webClient.get().uri("/equipment-service/v1/equipment/{equipmentId}", "10")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus().isOk()
        .expectBody(Equipment.class).isEqualTo(equipment);
  }
}
