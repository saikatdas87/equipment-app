package io.saikat.spring.elasticsearch.equipmentapp.service;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
  List<Equipment> fetchEquipments(String limit);

  Equipment indexEquipment(Equipment equipment);

  Optional<Equipment> fetchEquipmentById(String equipmentId);
}
