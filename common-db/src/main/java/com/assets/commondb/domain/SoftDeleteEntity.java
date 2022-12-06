package com.assets.commondb.domain;

import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * This class help us to follow to delete state of our entity.
 * <p>
 * Simply create field "deleted" on your table.
 * <p>
 * By default, entity creates as "deleted: false".
 * <p>
 * When you  delete entity, it is marks as "deleted: true".
 * <p>
 * Entity still alive in database.
 * <p>
 *
 * @author Rosyslav Balushchak
 * @see TimeEntity
 * @since 1.0.0-SNAPSHOT
 */
@Data
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class SoftDeleteEntity extends TimeEntity {
  @Builder.Default
  private Boolean deleted = false;
}
