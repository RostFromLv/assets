package com.assets.commondtos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Class is used to represent data transfer object for First entity
 *
 *
 * @see SoftDeleteDto
 * @since 1.0.0-SNAPSHOT
 * @author Rosyslav Balushchak
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirstDto extends SoftDeleteDto {

  private @Nullable Long id;

  private @NonNull String brand;

  private @NonNull String bodyType;

  private @NonNull Double wheelRadius;
}
