package io.saikat.spring.elasticsearch.equipmentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EquipmentAlreadyIndexException extends RuntimeException{
  public EquipmentAlreadyIndexException(String message) {
    super(message);
  }
}
