package io.saikat.spring.elasticsearch.equipmentapp.service;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.repository.EquipmentRepository;
import io.saikat.spring.elasticsearch.equipmentapp.exception.EquipmentAlreadyIndexException;
import io.saikat.spring.elasticsearch.equipmentapp.exception.InvalidRequestParamException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class EquipmentServiceImplTest {

  @MockBean
  private EquipmentRepository equipmentRepo;

  private EquipmentServiceImpl equipmentService;

  private final Sort sort = Sort.by(Sort.Direction.ASC, "contractEndDate");

  @BeforeEach
  public void init() {
    equipmentService = new EquipmentServiceImpl(equipmentRepo);
  }

  @Test
  public void fetchEquipmentsByFindALL() {
    val equipments = Flux.just(Equipment.builder().id("123").build());
    given(equipmentRepo.findAll(sort)).willReturn(equipments);
    val res = equipmentService.fetchEquipments("");

    assertThat(Objects.requireNonNull(res.count().block()).longValue()).isEqualTo(1L);
    assertThat(res.map(Equipment::getId).blockFirst()).isEqualTo("123");
  }

  @Test
  public void fetchEquipmentsByLimit() {
    val limit = "10";
    val equipments = Flux.just(Equipment.builder().id("123").build());
    given(equipmentRepo.findAll(sort))
        .willReturn(equipments);
    val res = equipmentService.fetchEquipments(limit);

    assertThat(Objects.requireNonNull(res.count().block())).isEqualTo(1L);
    assertThat(res.map(Equipment::getId).blockFirst()).isEqualTo("123");
  }

  @Test
  public void fetchEquipmentsThrowsExceptionForInvalidLimit() {
    try {
      equipmentService.fetchEquipments("limit");
      fail("Should have thrown exception");
    } catch (InvalidRequestParamException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid limit parameter : limit");
    }
  }

  @Test
  public void testIndexSuccess() {
    val equipment = Equipment.builder().id("123").build();
    given(equipmentRepo.findById(equipment.getId())).willReturn(Mono.empty());
    given(equipmentRepo.save(equipment)).willReturn(Mono.just(equipment));

    val res = equipmentService.indexEquipment(equipment);

    assertThat(Objects.requireNonNull(res.block()).getId()).isEqualTo(equipment.getId());
  }

  /*@Test
  public void failsToIndexIfAlreadyExists() {
    val equipment = Equipment.builder().id("123").build();
    given(equipmentRepo.findById(equipment.getId())).willReturn(Mono.just(equipment));
    val res = equipmentService.indexEquipment(equipment);
    StepVerifier.create(equipmentService.indexEquipment(equipment)).expectError(EquipmentAlreadyIndexException.class).verify();
  }*/
}
