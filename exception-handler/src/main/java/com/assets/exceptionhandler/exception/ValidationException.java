package com.assets.exceptionhandler.exception;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Customizing for handling handleMethodArgumentNotValid.
 * <p>
 * Show for fields with exception messages for violated fields with using @Valid
 *
 *
 *
 * @see javax.validation.Valid
 * @see org.springframework.web.bind.MethodArgumentNotValidException
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends Exception {
  private Exception exception;
  private List<ValidationFieldException> validationException;
}
