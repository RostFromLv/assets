package com.assets.exceptionhandler.exception;
import lombok.Data;

/**
 * Class that response for exception message in related field;
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class ValidationFieldException {
  private String field;
  private String exception;
}
