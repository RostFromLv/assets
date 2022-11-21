package com.assets.firstservice;

import com.assets.firstservice.domain.First;
import com.example.commondtos.models.FirstDto;
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

  protected FirstDto getCarDtoWithNullBrand(){
    firstDto.setBrand(null);
    return firstDto;
  }
  protected FirstDto getCarDtoWithNullId(){
    firstDto.setId(null);
    return firstDto;
  }
  protected First getCarWithNullId(){
    firstEntity.setId(null);
    return firstEntity;
  }
  protected First getCarWithNullBrand(){
    firstEntity.setBrand(null);
    return firstEntity;
  }

  protected Collection<FirstDto> getCollectionOfCarDto(){
    Collection<FirstDto> expected = new HashSet<>();
    expected.add(firstDto);
    expected.add(secondFirstDto);
    return expected;
  }

  protected Collection<First> getCollectionOfCarEntity(){
    Collection<First> expected = new HashSet<>();
    expected.add(firstEntity);
    expected.add(secondFirstEntity);
    return expected;
  }
}
