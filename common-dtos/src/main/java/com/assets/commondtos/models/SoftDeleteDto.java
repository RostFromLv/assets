package com.assets.commondtos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class SoftDeleteDto {

  @Builder.Default
  private Boolean deleted = Boolean.FALSE;
}
