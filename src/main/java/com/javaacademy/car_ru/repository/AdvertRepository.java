package com.javaacademy.car_ru.repository;

import com.javaacademy.car_ru.entyty.Advert;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class AdvertRepository {
    private final Map<String, Advert> adverts = new HashMap<>();

    public void addAdvert(Advert advert) {
        adverts.put(advert.getId(), advert);
    }

    public List<Advert> getAllAdvertsFromDate(LocalDate date) {
        return adverts.values()
                .stream()
                .filter(advert -> (advert.getPublicationDate().compareTo(date) == 0))
                .toList();
    }

    public boolean deleteById(String id) {
        return adverts.remove(id) != null;
    }

    public Optional<Advert> takeById(String id) {
        return Optional.ofNullable(adverts.get(id));
    }

    public List<Advert> takeByParameter(String brandName, String colour, BigDecimal price, String model) {
        List<Advert> filterList = new ArrayList<>(adverts.values());
        if (brandName != null) {
            filterList = new ArrayList<>(filterList
                    .stream()
                    .filter(advert -> advert.getBrandName().equals(brandName))
                    .toList());
        }
        if (colour != null) {
            filterList = new ArrayList<>(filterList
                    .stream()
                    .filter(advert -> advert.getColour().equals(colour))
                    .toList());
        }
        if (price != null) {
            filterList = new ArrayList<>(filterList
                    .stream()
                    .filter(advert -> advert.getPrice().compareTo(price) == 0)
                    .toList());
        }
        if (model != null) {
            filterList = new ArrayList<>(filterList
                    .stream()
                    .filter(advert -> advert.getModel().equals(model))
                    .toList());
        }
        return filterList;
    }
}
