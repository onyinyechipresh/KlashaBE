package com.example.KlashaBE;

import com.example.KlashaBE.apiRequest.*;
import com.example.KlashaBE.apiResponse.*;
import com.example.KlashaBE.services.serviceImplementation.CountryEntityServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryEntityServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CountryEntityServiceImpl countryEntityService;
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

    @Test
    public CountryEntityResponse testGetCountryEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        CountryPopulationRequest request = new CountryPopulationRequest();
        request.setCountry("Nigeria");
        HttpEntity<CountryPopulationRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<CountryPopulationResponse> populationResponse = restTemplate.exchange(countryPopulationApi, HttpMethod.POST, entity, CountryPopulationResponse.class);


        HttpHeaders cityHeaders = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryCapitalCityRequest cityRequest = new CountryCapitalCityRequest();
        request.setCountry("Nigeria");
        HttpEntity<CountryCapitalCityRequest> cityEntity = new HttpEntity<>(cityRequest,cityHeaders);
        ResponseEntity<CountryCapitalCityResponse> capitalCityResponse = restTemplate.exchange(countryCapitalCityApi,HttpMethod.POST,cityEntity,CountryCapitalCityResponse.class);


        HttpHeaders locationHeader = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryLocationRequest locationRequest = new CountryLocationRequest();
        request.setCountry("Nigeria");
        HttpEntity<CountryLocationRequest> locationEntity = new HttpEntity<>(locationRequest,locationHeader);
        ResponseEntity<CountryLocationResponse> locationResponse = restTemplate.exchange(countryLocationApi,HttpMethod.POST,locationEntity,CountryLocationResponse.class);


        HttpHeaders currencyHeaders = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryCurrencyRequest currencyRequest = new CountryCurrencyRequest();
        request.setCountry("Nigeria");
        HttpEntity<CountryCurrencyRequest> currencyEntity = new HttpEntity<>(currencyRequest,currencyHeaders);
        ResponseEntity<CountryCurrencyResponse> currencyResponse = restTemplate.exchange(countryCurrencyApi,HttpMethod.POST,currencyEntity,CountryCurrencyResponse.class);


        HttpHeaders isoHeaders = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryIsoRequest isoRequest = new CountryIsoRequest();
        request.setCountry("Nigeria");
        HttpEntity<CountryIsoRequest> isoEntity = new HttpEntity<>(isoRequest,isoHeaders);
        ResponseEntity<CountryIsoResponse> isoResponse = restTemplate.exchange(countryIsoApi,HttpMethod.POST,isoEntity,CountryIsoResponse.class);

        CountryEntityResponse result = countryEntityService.getCountryEntity("Nigeria");

        // Assert
        assertNotNull(result);
        assertEquals(populationResponse, 4562781);
        assertEquals(capitalCityResponse, "Abuja");
        assertEquals(currencyResponse, "NGN");
        assertEquals(locationResponse, 8, String.valueOf(10));
        assertEquals(isoResponse, "NG","NGA");
        return result;
    }
}
