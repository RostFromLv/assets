package com.assets.secondservice;

import com.assets.commondb.domain.Second;
import com.assets.commondtos.models.SecondDto;
import java.util.Collection;
import java.util.HashSet;

@SuppressWarnings(value = "argument") // For testing cases
public class AbstractTest {
  protected final Long id = 1L;
  protected final Long sId = 2L;
  protected final Float age = 15F;
  protected final Float sAge = 20F;
  protected final String name = "Roman";
  protected final String sName = "Rostik";
  protected final Long firstId = 1l;
  protected final Long sFirstId = 5l;
  protected final String anotherName = "Peter";


  protected final SecondDto secondDto = SecondDto.builder()
      .id(id)
      .age(age)
      .name(name)
      .firstId(firstId).build();

  protected final Second secondEntity = Second.builder()
      .id(id)
      .age(sAge)
      .name(sName)
      .firstId(sFirstId).build();

  protected final SecondDto secondSecondDto = SecondDto.builder()
      .id(sId)
      .age(sAge)
      .name(sName)
      .firstId(sFirstId).build();

  protected final Second secondSecondEntity = Second.builder()
      .id(sId)
      .age(sAge)
      .name(sName)
      .firstId(sFirstId).build();

  protected final SecondDto anotherSecondDto = SecondDto.builder()
      .id(sId)
      .age(sAge)
      .name(anotherName)
      .firstId(firstId).build();
  protected final Second anotherSecond = Second.builder()
      .id(null)
      .age(age)
      .name(sName)
      .firstId(firstId).build();

  protected SecondDto getWorkerDtoWithNullName() {
    secondDto.setName(null);
    return secondDto;
  }

  protected SecondDto getFirstDtoWithNullId() {
    secondDto.setId(null);
    return secondDto;
  }

  protected Second getFirstWithNullId() {
    secondEntity.setId(null);
    return secondEntity;
  }

  protected Second getFirstWithNullBrand() {
    secondEntity.setName(null);
    return secondEntity;
  }

  protected Collection<SecondDto> getCollectionOfWorkerDto() {
    Collection<SecondDto> expected = new HashSet<>();
    expected.add(secondDto);
    expected.add(secondSecondDto);
    return expected;
  }

  protected Collection<Second> getCollectionOfWorkerEntity() {
    Collection<Second> expected = new HashSet<>();
    expected.add(secondEntity);
    expected.add(secondSecondEntity);
    return expected;
  }
}
