package com.javaacademy.car_ru.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertDtoRq {
    private String brandName;
    private String colour;
    private BigDecimal price;
    private String model;
    private LocalDate publicationDate;
}
