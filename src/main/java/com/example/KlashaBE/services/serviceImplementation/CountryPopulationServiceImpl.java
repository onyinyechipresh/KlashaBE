package com.example.KlashaBE.services.serviceImplementation;

import com.example.KlashaBE.apiRequest.*;
import com.example.KlashaBE.apiResponse.*;
import com.example.KlashaBE.services.CountryPopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CountryPopulationServiceImpl implements CountryPopulationService {
    private final RestTemplate restTemplate;
    @Value("${all.country.population.endpoint:https://countriesnow.space/api/v0.1/countries/population}")
    private String countryPopulationApi;
    @Value("${capital.cities.endpoint:https://countriesnow.space/api/v0.1/countries/capital}")
    private String countryCapitalCityApi;
    @Value("${country.location.endpoint:https://countriesnow.space/api/v0.1/countries/positions}")
    private String countryLocationApi;
    @Value("${country.currency.endpoint:https://countriesnow.space/api/v0.1/countries/currency}")
    private String countryCurrencyApi;
    @Value("${country.iso.endpoint:https://countriesnow.space/api/v0.1/countries/iso}")
    private String countryIsoApi;


    public CountryEntityResponse getCountryEntity(String country){
        CountryEntityResponse entityResponse = new CountryEntityResponse();
        entityResponse.setPopulation(getCountryPopulation(CountryPopulationRequest.builder()
                .country(country)
                .build()));
        entityResponse.setCapital(getCountryCapitalCities(CountryCapitalCityRequest.builder()
                .country(country)
                .build()));
        entityResponse.setCurrency(getCountryCurrency(CountryCurrencyRequest.builder()
                .country(country)
                .build()));
        entityResponse.setLocation(getCountryLocation(CountryLocationRequest.builder()
                .country(country)
                .build()));
        entityResponse.setIso(getCountryIso(CountryIsoRequest.builder()
                .country(country)
                .build()));

        return entityResponse;

    }
    public CountryPopulationResponse getCountryPopulation(CountryPopulationRequest country){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryPopulationRequest request = new CountryPopulationRequest();
        request.setCountry(country.getCountry());
        HttpEntity<CountryPopulationRequest> entity = new HttpEntity<>(request,headers);
        ResponseEntity<CountryPopulationResponse> populationResponse = restTemplate.exchange(countryPopulationApi,HttpMethod.POST,entity,CountryPopulationResponse.class);
        System.out.println("PopulationResponse : : "+ populationResponse);
        return populationResponse.getBody();
    }

    public CountryCapitalCityResponse getCountryCapitalCities(CountryCapitalCityRequest country){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryCapitalCityRequest request = new CountryCapitalCityRequest();
        request.setCountry(country.getCountry());
        HttpEntity<CountryCapitalCityRequest> entity = new HttpEntity<>(request,headers);
        ResponseEntity<CountryCapitalCityResponse> capitalCityResponse = restTemplate.exchange(countryCapitalCityApi,HttpMethod.POST,entity,CountryCapitalCityResponse.class);
        return capitalCityResponse.getBody();
    }
    public CountryLocationResponse getCountryLocation(CountryLocationRequest country){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryLocationRequest request = new CountryLocationRequest();
        request.setCountry(country.getCountry());
        HttpEntity<CountryLocationRequest> entity = new HttpEntity<>(request,headers);
        ResponseEntity<CountryLocationResponse> locationResponse = restTemplate.exchange(countryLocationApi,HttpMethod.POST,entity,CountryLocationResponse.class);
        return locationResponse.getBody();
    }
    public CountryCurrencyResponse getCountryCurrency(CountryCurrencyRequest country){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryCurrencyRequest request = new CountryCurrencyRequest();
        request.setCountry(country.getCountry());
        HttpEntity<CountryCurrencyRequest> entity = new HttpEntity<>(request,headers);
        ResponseEntity<CountryCurrencyResponse> currencyResponse = restTemplate.exchange(countryCurrencyApi,HttpMethod.POST,entity,CountryCurrencyResponse.class);
        return currencyResponse.getBody();
    }
    public CountryIsoResponse getCountryIso(CountryIsoRequest country){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryIsoRequest request = new CountryIsoRequest();
        request.setCountry(country.getCountry());
        HttpEntity<CountryIsoRequest> entity = new HttpEntity<>(request,headers);
        ResponseEntity<CountryIsoResponse> isoResponse = restTemplate.exchange(countryIsoApi,HttpMethod.POST,entity,CountryIsoResponse.class);
        return isoResponse.getBody();
    }
}
