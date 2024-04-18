package com.javaacademy.car_ru.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertDTO {
    String brandName;
    String colour;
    BigDecimal price;
    String model;
    LocalDate publicationDate;
}
