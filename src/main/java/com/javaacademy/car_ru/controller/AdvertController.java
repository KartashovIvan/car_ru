package com.javaacademy.car_ru.controller;

import com.javaacademy.car_ru.dto.AdvertDTO;
import com.javaacademy.car_ru.entyty.Advert;
import com.javaacademy.car_ru.service.AdvertService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;

    @PostMapping
    public ResponseEntity<String> createAdvert(@RequestBody AdvertDTO advertDTO) {
        Advert advert = advertService.createAdvert(advertDTO);
        return ResponseEntity.status(CREATED).body("Создали обьявление с id " + advert.getId());
    }

    @GetMapping
    public ResponseEntity<List<Advert>> getAllAdvertsFromDate(@RequestParam LocalDate date) {
        List<Advert> advertsFromDate = advertService.getAllAdvertsFromDate(date);
        return advertsFromDate.size() > 0
                ? ResponseEntity.status(FOUND).body(advertsFromDate)
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam String id) {
        return advertService.deleteById(id)
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advert> takeById(@PathVariable String id) {
        Optional<Advert> advert = advertService.takeById(id);
        return advert.isPresent()
                ? ResponseEntity.status(FOUND).body(advert.orElseThrow())
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Advert>> takeByParameters(@RequestParam(required = false) String brandName,
                                         @RequestParam(required = false) String colour,
                                         @RequestParam(required = false) BigDecimal price,
                                         @RequestParam(required = false) String model) {
        List<Advert> adverts = advertService.takeByParameters(brandName, colour, price, model);
        return adverts.size() > 0
                ? ResponseEntity.status(OK).body(adverts)
                : ResponseEntity.status(NOT_FOUND).build();
    }
}
