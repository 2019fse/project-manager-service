package com.fse.pm.common.config;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import static org.springframework.util.Assert.isInstanceOf;

public class ModelMapperConfigTest {

    @Test
    public void modelMapper() {
        isInstanceOf(ModelMapper.class, new ModelMapperConfig().modelMapper());
    }
}