package com.assets.commondtos.models;

import com.assets.commondb.domain.SoftDeleteEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Email;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends SoftDeleteEntity {

  private @Nullable Long id;

  private @NonNull String name;

  private @NonNull String lastName;

  private @NonNull @Email String email;

  private @NonNull String password;
}
