package com.javaacademy.car_ru.service;

import com.javaacademy.car_ru.dto.AdvertDtoRq;
import com.javaacademy.car_ru.dto.AdvertDtoRs;
import com.javaacademy.car_ru.entity.Advert;
import com.javaacademy.car_ru.repository.AdvertRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertDtoRs createAdvert(AdvertDtoRq advertDto) {
        Advert advert = convertToAdvertEntity(advertDto);
        advertRepository.addAdvert(advert);
        return convertToAdvertDtoRs(advert);
    }

    public List<AdvertDtoRs> getAllAdvertsFromDate(LocalDate date) {
        List<Advert> advertsFromDate = new ArrayList<>();
        return advertRepository.getAllAdvertsFromDate(date).stream()
                .map(this::convertToAdvertDtoRs)
                .toList();
    }

    public boolean deleteById(String id) {
        return advertRepository.deleteById(id);
    }

    public Optional<AdvertDtoRs> takeById(String id) {
        return advertRepository.takeById(id)
                .map(this::convertToAdvertDtoRs);
    }

    public List<AdvertDtoRs> takeByParameters(String brandName, String colour, BigDecimal price, String model) {
        List<Advert> adverts = advertRepository.takeByParameter(brandName, colour, price, model);
        return adverts.stream()
                .map(this::convertToAdvertDtoRs)
                .toList();
    }

    private Advert convertToAdvertEntity(AdvertDtoRq advertDto) {
        LocalDate date = advertDto.getPublicationDate() == null ? LocalDate.now() : advertDto.getPublicationDate();
        String id = UUID.randomUUID().toString();
        return new Advert(id,
                advertDto.getBrandName(),
                advertDto.getColour(),
                advertDto.getPrice(),
                advertDto.getModel(),
                date);
    }

    private AdvertDtoRs convertToAdvertDtoRs(Advert advert) {
        return new AdvertDtoRs(advert.getId(),
                advert.getBrandName(),
                advert.getColour(),
                advert.getPrice(),
                advert.getModel(),
                advert.getPublicationDate());
    }
}
