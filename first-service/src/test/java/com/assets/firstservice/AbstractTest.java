package com.assets.firstservice;

import com.assets.commondb.domain.First;
import com.assets.commondtos.models.FirstDto;
import java.util.Collection;
import java.util.HashSet;

@SuppressWarnings(value = "argument") // For testing cases
public class AbstractTest {
  protected final Long id = 1L;
  protected final Long sId = 2L;
  protected final String brand = "Audi";
  protected final String sBrand = "Toyota";
  protected final Double wheelRadius = 15D;
  protected final String bodyType = "wagon";
  protected final String secondBodyType = "coupe";

  protected final FirstDto firstDto = FirstDto.builder()
      .id(id)
      .brand(brand)
      .bodyType(bodyType)
      .wheelRadius(wheelRadius).build();

  protected final First firstEntity = First.builder()
      .id(id)
      .brand(brand)
      .bodyType(bodyType)
      .wheelRadius(wheelRadius).build();

  protected final FirstDto secondFirstDto = FirstDto.builder()
      .id(sId)
      .brand(brand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();

  protected final First secondFirstEntity = First.builder()
      .id(sId)
      .brand(brand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();

  protected final FirstDto anotherFirstDto = FirstDto.builder()
      .id(sId)
      .brand(sBrand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();
  protected final First anotherFirst = First.builder()
      .id(null)
      .brand(sBrand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();

  protected FirstDto getFirstDtoWithNullBrand(){
    firstDto.setBrand(null);
    return firstDto;
  }
  protected FirstDto getFirstDtoWithNullId(){
    firstDto.setId(null);
    return firstDto;
  }
  protected First getFirstWithNullId(){
    firstEntity.setId(null);
    return firstEntity;
  }
  protected First getFirstWithNullBrand(){
    firstEntity.setBrand(null);
    return firstEntity;
  }

  protected Collection<FirstDto> getCollectionOfFirstDto(){
    Collection<FirstDto> expected = new HashSet<>();
    expected.add(firstDto);
    expected.add(secondFirstDto);
    return expected;
  }

  protected Collection<First> getCollectionOfFirstEntity(){
    Collection<First> expected = new HashSet<>();
    expected.add(firstEntity);
    expected.add(secondFirstEntity);
    return expected;
  }
}
