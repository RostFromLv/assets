package com.assets.mapper;

import java.lang.reflect.Field;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract mapper created for future customizing mapper.
 *
 * @param <Entity> simple JPA entity for converting to dto
 * @param <DTO>     transport object for converting to entity
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 */
public abstract class AbstractMapper<Entity, DTO> implements InitializingBean {

  public abstract DTO toDto(final Entity entity);

  public abstract Entity toEntity(final DTO dto);

  public abstract void updateProperties(final DTO dto, final @MappingTarget Entity entity);

  @Override
  public final void afterPropertiesSet() throws Exception {
    //noinspection rawtypes
    Class<? extends AbstractMapper> aClass = this.getClass();
    for (Field f : aClass.getDeclaredFields()) {
      if (!f.isAnnotationPresent(Autowired.class)) {
        continue;
      }

      synchronized (this) {
        boolean isAccessible = f.canAccess(this);
        if (!isAccessible) {
          f.setAccessible(true);
        }

        Object val = f.get(this);
        f.setAccessible(isAccessible);
        if (val == null) {
          throw new IllegalStateException(
              String.format("Field '%s' was not initialized", f.getName()));
        }
      }
    }
  }
}
