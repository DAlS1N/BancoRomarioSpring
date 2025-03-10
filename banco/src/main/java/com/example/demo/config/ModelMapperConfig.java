package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper config(){
        ModelMapper modelMapper =
                new ModelMapper();
        modelMapper
                .getConfiguration()
                .isSkipNullEnabled();
        return modelMapper;
    }
}
