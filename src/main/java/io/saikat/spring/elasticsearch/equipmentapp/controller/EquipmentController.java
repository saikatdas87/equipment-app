package io.saikat.spring.elasticsearch.equipmentapp.controller;

import io.saikat.spring.elasticsearch.equipmentapp.service.EquipmentService;
import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EquipmentController {

  private final EquipmentService equipmentService;

  @GetMapping(value = {"/equipment-service/v1/equipment/search", "/equipment-service/v1/equipment/search/{limit}"})
  public Flux<Equipment> list(@PathVariable Optional<String> limit) {
    log.debug("Fetching a list of equipment");
    return equipmentService.fetchEquipments(limit.orElse(""));
  }

  @PostMapping("/equipment-service/v1/equipment/index")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Equipment> index(@Valid @RequestBody Equipment equipment) {
    return equipmentService.indexEquipment(equipment);
  }

  @GetMapping(value = {"/equipment-service/v1/equipment/{equipmentId}"})
  public Mono<Equipment> fetchEquipmentById(@PathVariable String equipmentId) {
    log.debug("Fetching equipment by Id " + equipmentId);
    return equipmentService.fetchEquipmentById(equipmentId);
  }
}
