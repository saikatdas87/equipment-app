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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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
    val equipments = List.of(Equipment.builder().id("123").build());
    given(equipmentRepo.findAll(sort)).willReturn(equipments);
    val res = equipmentService.fetchEquipments("");

    assertThat(res.size()).isEqualTo(1);
    assertThat(res.get(0).getId()).isEqualTo("123");
  }

  @Test
  public void fetchEquipmentsByLimit() {
    val limit = "10";
    val equipments = List.of(Equipment.builder().id("123").build());
    given(equipmentRepo.findAll(PageRequest.of(0,Integer.parseInt(limit), sort)))
        .willReturn(new PageImpl<>(equipments));
    val res = equipmentService.fetchEquipments(limit);

    assertThat(res.size()).isEqualTo(1);
    assertThat(res.get(0).getId()).isEqualTo("123");
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
    given(equipmentRepo.findById(equipment.getId())).willReturn(Optional.empty());
    given(equipmentRepo.save(equipment)).willReturn(equipment);

    val res = equipmentService.indexEquipment(equipment);

    assertThat(res.getId()).isEqualTo(equipment.getId());
  }

  @Test
  public void failsToIndexIfAlreadyExists() {
    val equipment = Equipment.builder().id("123").build();
    try {
      given(equipmentRepo.findById(equipment.getId())).willReturn(Optional.of(equipment));
      equipmentService.indexEquipment(equipment);
      fail("Should have thrown exception");
    } catch (EquipmentAlreadyIndexException e) {
      assertThat(e.getMessage()).isEqualTo("Equipment already exists with id : 123");
    }
  }
}
