package com.javaacademy.car_ru.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDtoRs {
    private String id;
    private String brandName;
    private String colour;
    private BigDecimal price;
    private String model;
    private LocalDate publicationDate;
}
