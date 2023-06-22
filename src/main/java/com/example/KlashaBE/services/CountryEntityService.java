package com.example.KlashaBE.services;

import com.example.KlashaBE.apiRequest.*;
import com.example.KlashaBE.apiResponse.*;

public interface CountryEntityService {
    CountryEntityResponse getCountryEntity(String country);
    CountryPopulationResponse getCountryPopulation(CountryPopulationRequest country);
    CountryCapitalCityResponse getCountryCapitalCities(CountryCapitalCityRequest country);
    CountryLocationResponse getCountryLocation(CountryLocationRequest country);
    CountryCurrencyResponse getCountryCurrency(CountryCurrencyRequest country);
    CountryIsoResponse getCountryIso(CountryIsoRequest country);
}
