package com.javaacademy.car_ru.service;

import com.javaacademy.car_ru.dto.AdvertDTO;
import com.javaacademy.car_ru.entyty.Advert;
import com.javaacademy.car_ru.repository.AdvertRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;

    public Advert createAdvert(AdvertDTO advertDTO) {
        LocalDate date = advertDTO.getPublicationDate() == null ? LocalDate.now() : advertDTO.getPublicationDate();
        String id = UUID.randomUUID().toString();
        Advert advert = new Advert(id,
                advertDTO.getBrandName(),
                advertDTO.getColour(),
                advertDTO.getPrice(),
                advertDTO.getModel(),
                date);
        advertRepository.addAdvert(advert);
        return advert;
    }

    public List<Advert> getAllAdvertsFromDate(LocalDate date) {
        return advertRepository.getAllAdvertsFromDate(date);
    }

    public boolean deleteById(String id) {
        return advertRepository.deleteById(id);
    }

    public Optional<Advert> takeById(String id) {
        return advertRepository.takeById(id);
    }

    public List<Advert> takeByParameters(String brandName, String colour, BigDecimal price, String model) {
        return advertRepository.takeByParameter(brandName, colour, price, model);
    }
}
