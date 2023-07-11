package com.example.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String telephone;
    private String username;
    private String password;
    private String brand;
    private String model;
    private String engineType;


}
