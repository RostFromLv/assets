package com.assets.commondb.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * This class help us to follow the create and update Date.
 * <p>
 * By default, entity creates with  "updateAt" and "createdAt" dates with current date.
 * <p>
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

  @CreatedDate
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;
}
