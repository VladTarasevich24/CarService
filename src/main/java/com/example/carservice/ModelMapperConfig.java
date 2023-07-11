package com.example.carservice;

import com.example.carservice.dto.UserRegistrationDto;
import com.example.carservice.entity.Car;
import com.example.carservice.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(UserRegistrationDto.class, User.class)
                .addMapping(UserRegistrationDto::getTelephone, User::setTelephone)
                .addMapping(UserRegistrationDto::getUsername, User::setUsername)
                .addMapping(UserRegistrationDto::getPassword, User::setPassword);

        modelMapper.createTypeMap(UserRegistrationDto.class, Car.class)
                .addMapping(UserRegistrationDto::getBrand, Car::setBrand)
                .addMapping(UserRegistrationDto::getModel, Car::setModel)
                .addMapping(UserRegistrationDto::getEngineType, Car::setEngineType);

        return modelMapper;
    }
}
