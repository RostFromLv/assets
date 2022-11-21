package com.assets.service;

import com.assets.domain.Car;
import com.assets.mapper.AbstractMapper;
import com.assets.mapper.CarMapper;
import com.example.commondtos.models.FirstDto;
import org.springframework.stereotype.Service;

/**
 * Example of use abstract service.
 * <p>
 * For implementation, you need:
 * 1. Crate entity
 * <p>
 * 2. Create Dto
 * <p>
 * 3. Create customized mapper
 * <p>
 * 4. Create customized repository
 * <p>
 * 5. Create customized  interface with methods for implementation
 *
 * <p>
 *
 * @author Rosyslav Balushchak
 * @since 1.0.0-SNAPSHOT
 * @see AbstractService
 * @see AbstractMapper
 */
@Service
public class CarServiceImpl extends AbstractServiceImpl<Car, FirstDto, Long, CarMapper, CarRepository>
    implements CarService {
}
