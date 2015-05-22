package com.wmv.poc.gator.integration.util;

import com.wmv.poc.gator.integration.model.dto.Order;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by wvergara on 5/8/15.
 */
@Component
public class OrderMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(Order.class, com.wmv.poc.commons.model.dto.Order.class)
                .byDefault()
                .register();


    }
}