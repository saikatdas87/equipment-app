package io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.repository;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EquipmentRepository extends ElasticsearchRepository<Equipment, String> {
}
