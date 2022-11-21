package com.example.commondtos.models;

import com.example.commondtos.domain.SoftDeleteEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Simple example of data transport object.
 *
 *
 * @see SoftDeleteEntity
 * @since 1.0.0-SNAPSHOT
 * @author Rosyslav Balushchak
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirstDto extends SoftDeleteEntity {
  private @Nullable Long id;

  private @NonNull String brand;

  private @NonNull String bodyType;

  private @NonNull Double wheelRadius;
}
