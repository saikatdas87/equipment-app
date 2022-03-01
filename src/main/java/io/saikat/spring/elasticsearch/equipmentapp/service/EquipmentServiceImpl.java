package io.saikat.spring.elasticsearch.equipmentapp.service;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.repository.EquipmentRepository;
import io.saikat.spring.elasticsearch.equipmentapp.exception.EquipmentAlreadyIndexException;
import io.saikat.spring.elasticsearch.equipmentapp.exception.InvalidRequestParamException;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
  private final EquipmentRepository equipmentRepo;

  @Override
  public List<Equipment> fetchEquipments(String limit) {
    try {
      val sort = Sort.by(Sort.Direction.ASC, "contractEndDate");
      if (!ObjectUtils.isEmpty(limit)) {
        val equipmentPage = equipmentRepo.findAll(PageRequest.of(0, Integer.parseInt(limit), sort));
        return equipmentPage.get().collect(Collectors.toList());
      }
      return StreamSupport.stream(equipmentRepo.findAll(sort).spliterator(), false).toList();
    } catch (NumberFormatException ne) {
      throw new InvalidRequestParamException("Invalid limit parameter : " + limit);
    }
  }

  @Override
  public Equipment indexEquipment(Equipment equipment) {
    if (isEquipmentAlreadyIndexed(equipment.getId())) {
      throw new EquipmentAlreadyIndexException("Equipment already exists with id : " + equipment.getId());
    }
    return equipmentRepo.save(equipment);
  }

  @Override
  public Optional<Equipment> fetchEquipmentById(String equipmentId) {
    return equipmentRepo.findById(equipmentId);
  }

  private Boolean isEquipmentAlreadyIndexed(String equipmentId) {
    val equipment = equipmentRepo.findById(equipmentId);
    return equipment.isPresent();
  }
}
