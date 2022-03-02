package io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.repository;

import io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface EquipmentRepository extends ReactiveElasticsearchRepository<Equipment, String> {
}
