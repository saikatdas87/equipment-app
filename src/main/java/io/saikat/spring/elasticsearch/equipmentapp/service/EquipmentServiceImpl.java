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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
  private final EquipmentRepository equipmentRepo;

  @Override
  public Flux<Equipment> fetchEquipments(String limit) {
    try {
      val sort = Sort.by(Sort.Direction.ASC, "contractEndDate");
      if (!ObjectUtils.isEmpty(limit)) {
        val equipmentPage = equipmentRepo.findAll(sort);
        return equipmentPage.take(Integer.parseInt(limit));
      }
      return equipmentRepo.findAll(sort);
    } catch (NumberFormatException ne) {
      throw new InvalidRequestParamException("Invalid limit parameter : " + limit);
    }
  }

  @Override
  public Mono<Equipment> indexEquipment(Equipment equipment) {
    return isEquipmentAlreadyIndexed(equipment.getId())
        .flatMap(__ -> Mono.<Equipment>error(new EquipmentAlreadyIndexException("Equipment already exists with id : " + equipment.getId())))
        .switchIfEmpty(equipmentRepo.save(equipment));
  }

  @Override
  public Mono<Equipment> fetchEquipmentById(String equipmentId) {
    return equipmentRepo.findById(equipmentId);
  }

  private Mono<Equipment> isEquipmentAlreadyIndexed(String equipmentId) {
    return equipmentRepo.findById(equipmentId);
  }
}
