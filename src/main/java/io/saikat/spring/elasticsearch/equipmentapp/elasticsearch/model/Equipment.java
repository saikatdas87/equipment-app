package io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "equipment")
@Data
@Builder
public class Equipment {
  @Id
  private String id;

  @Field(type = FieldType.Date, name = "contractStartDate")
  private Date contractStartDate;

  @Field(type = FieldType.Date, name = "contractEndDate")
  private Date contractEndDate;

  @Field(type = FieldType.Keyword, name = "status")
  private Status status;

  @Field(type = FieldType.Object, name = "address")
  private Address address;

}
