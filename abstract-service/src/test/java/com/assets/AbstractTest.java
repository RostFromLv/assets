package com.assets;

import com.assets.domain.Car;
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

  protected final Car carEntity = Car.builder()
      .id(id)
      .brand(brand)
      .bodyType(bodyType)
      .wheelRadius(wheelRadius).build();

  protected final FirstDto secondFirstDto = FirstDto.builder()
      .id(sId)
      .brand(brand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();

  protected final Car secondCarEntity = Car.builder()
      .id(sId)
      .brand(brand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();

  protected final FirstDto anotherFirstDto = FirstDto.builder()
      .id(sId)
      .brand(sBrand)
      .bodyType(secondBodyType)
      .wheelRadius(wheelRadius).build();
  protected final Car anotherCar = Car.builder()
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
  protected Car getCarWithNullId(){
    carEntity.setId(null);
    return carEntity;
  }
  protected Car getCarWithNullBrand(){
    carEntity.setBrand(null);
    return carEntity;
  }

  protected Collection<FirstDto> getCollectionOfCarDto(){
    Collection<FirstDto> expected = new HashSet<>();
    expected.add(firstDto);
    expected.add(secondFirstDto);
    return expected;
  }

  protected Collection<Car> getCollectionOfCarEntity(){
    Collection<Car> expected = new HashSet<>();
    expected.add(carEntity);
    expected.add(secondCarEntity);
    return expected;
  }
}
