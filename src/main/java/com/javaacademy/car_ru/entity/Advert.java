package com.javaacademy.car_ru.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

@Data
public class Advert {
    @NonNull
    private String id;
    @NonNull
    private String brandName;
    @NonNull
    private String colour;
    @NonNull
    private BigDecimal price;
    @NonNull
    private String model;
    @NonNull
    private LocalDate publicationDate;
}
