package io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model;

import lombok.Data;

@Data
public class Address {

  private String addressLine1;

  private String addressLine2;

  private String city;

  private String country;

  private Integer postalCode;
}
