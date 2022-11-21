package com.assets.firstservice.domain;

import com.example.commondtos.domain.SoftDeleteEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

/**
 * It is a simple example of a regular entity that is ready for use.
 * <p>
 * In this case we use softDelete and timeEntity for time and deleting audits.
 *
 * @author Rosyslav Balushchak
 * @see SoftDeleteEntity
 * @since 1.0.0-SNAPSHOT
 */
@Data
@Table(name = "cars")
@Entity(name = "cars")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE  cars SET  deleted = true Where id =?")
public class First extends SoftDeleteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String brand;

  @Column
  private String bodyType;

  @Column
  private Double wheelRadius;
}
