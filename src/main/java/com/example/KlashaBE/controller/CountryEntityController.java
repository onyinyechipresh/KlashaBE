package com.example.KlashaBE.controller;

import com.example.KlashaBE.services.CountryEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CountryEntityController {
    private final CountryEntityService countryEntityService;
    @GetMapping("/country-entity")
    public void getCountryEntity(@RequestParam (value = "country") String country){
        countryEntityService.getCountryEntity(country);
    }
}
