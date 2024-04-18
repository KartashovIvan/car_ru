package com.javaacademy.car_ru.repository;

import com.javaacademy.car_ru.entity.Advert;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
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
        List<Advert> allAdverts = new ArrayList<>(adverts.values());
        List<Advert> filterList = new ArrayList<>();
        if (brandName != null) {
            filterList = filterListByParameter(allAdverts,
                    filterList,
                    advert -> advert.getBrandName().equals(brandName));
        }
        if (colour != null) {
            filterList = filterListByParameter(allAdverts,
                    filterList,
                    advert -> advert.getColour().equals(colour));
        }
        if (price != null) {
            filterList = filterListByParameter(allAdverts,
                    filterList,
                    advert -> advert.getPrice().compareTo(price) == 0);
        }
        if (model != null) {
            filterList = filterListByParameter(allAdverts,
                    filterList,
                    advert -> advert.getModel().equals(model));
        }
        return filterList;
    }

    private List<Advert> filterListByParameter(List<Advert> adverts,
                                               List<Advert> filterList,
                                               Predicate<Advert> filter) {
        if (filterList.isEmpty()) {
            return adverts.stream()
                    .filter(filter)
                    .toList();
        }
        return filterList.stream()
                .filter(filter)
                .toList();
    }
}
