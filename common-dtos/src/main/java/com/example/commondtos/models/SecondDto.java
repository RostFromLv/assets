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

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondDto extends SoftDeleteEntity {

  @Nullable
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private Float age;

  @NonNull
  private Long carId;
}
