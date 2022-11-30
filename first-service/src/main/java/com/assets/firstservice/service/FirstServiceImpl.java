package com.assets.firstservice.service;

import com.assets.commondb.domain.First;
import com.assets.commondtos.models.FirstDto;
import com.assets.firstservice.mapper.FirstMapper;
import com.assets.service.AbstractServiceImpl;
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
 * @see AbstractServiceImpl
 * @see FirstMapper
 */
@Service
public class FirstServiceImpl extends AbstractServiceImpl<First, FirstDto, Long, FirstMapper, FirstRepository>
    implements FirstService {

}
