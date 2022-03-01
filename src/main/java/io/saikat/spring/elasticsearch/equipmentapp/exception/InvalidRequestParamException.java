package io.saikat.spring.elasticsearch.equipmentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestParamException extends RuntimeException {
  public InvalidRequestParamException(String message) {
    super(message);
  }
}
