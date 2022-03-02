package io.saikat.spring.elasticsearch.equipmentapp.service;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
  Flux<Equipment> fetchEquipments(String limit);

  Mono<Equipment> indexEquipment(Equipment equipment);

  Mono<Equipment> fetchEquipmentById(String equipmentId);

}
