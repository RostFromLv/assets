package com.assets.exceptionhandler.exception;

import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * The class that represent structure of exception request.
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class Exception {
  private HttpStatus status;
  private String message;
  private LocalDateTime timestamp;
}
