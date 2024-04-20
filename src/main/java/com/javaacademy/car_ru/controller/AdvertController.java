package com.javaacademy.car_ru.controller;

import static org.springframework.http.HttpStatus.*;

import com.javaacademy.car_ru.dto.AdvertDtoRq;
import com.javaacademy.car_ru.dto.AdvertDtoRs;
import com.javaacademy.car_ru.service.AdvertService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;

    @PostMapping
    public ResponseEntity<String> createAdvert(@RequestBody AdvertDtoRq advertDto) {
        AdvertDtoRs advert = advertService.createAdvert(advertDto);
        return ResponseEntity.status(CREATED).body("Создали обьявление с id " + advert.getId());
    }

    @GetMapping
    public List<AdvertDtoRs> getAllAdvertsFromDate(@RequestParam LocalDate date) {
        return advertService.getAllAdvertsFromDate(date);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam String id) {
        return advertService.deleteById(id)
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertDtoRs> takeById(@PathVariable String id) {
        Optional<AdvertDtoRs> advert = advertService.takeById(id);
        return advert.isPresent()
                ? ResponseEntity.status(FOUND).body(advert.orElseThrow())
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AdvertDtoRs>> takeByParameters(@RequestParam(required = false) String brandName,
                                         @RequestParam(required = false) String colour,
                                         @RequestParam(required = false) BigDecimal price,
                                         @RequestParam(required = false) String model) {
        List<AdvertDtoRs> adverts = advertService.takeByParameters(brandName, colour, price, model);
        return adverts.size() > 0
                ? ResponseEntity.status(OK).body(adverts)
                : ResponseEntity.status(NOT_FOUND).build();
    }
}
