package com.example.KlashaBE;

import com.example.KlashaBE.apiRequest.CountryCurrencyRequest;
import com.example.KlashaBE.apiRequest.CurrencyRequest;
import com.example.KlashaBE.apiResponse.ConvertedCurrencyResponse;
import com.example.KlashaBE.apiResponse.CountryCurrencyResponse;
import com.example.KlashaBE.services.serviceImplementation.CountryCurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryCurrencyServiceImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Value("${country.currency.endpoint:https://countriesnow.space/api/v0.1/countries/currency}")
    private String countryCurrencyApi;

    @InjectMocks
    private CountryCurrencyServiceImpl countryCurrencyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testConvertCurrency_EURToNGN() {
        CurrencyRequest request = new CurrencyRequest();
        request.setCountry("Germany");
        request.setAmount(BigDecimal.valueOf(100));
        request.setTargetCurrency("NGN");

        CountryCurrencyResponse countryCurrencyResponse = new CountryCurrencyResponse();
        countryCurrencyResponse.getData().setCurrency("EUR");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        CountryCurrencyRequest currencyRequest = new CountryCurrencyRequest();
        currencyRequest.setCountry(request.getCountry());
        HttpEntity<CountryCurrencyRequest> entity = new HttpEntity<>(currencyRequest, headers);
        ResponseEntity<CountryCurrencyResponse> currencyResponse = restTemplate.exchange(countryCurrencyApi, HttpMethod.POST, entity, CountryCurrencyResponse.class);
        String sourceCurrency = currencyResponse.getBody().getData().getCurrency();


        ConvertedCurrencyResponse result = countryCurrencyService.convertCurrency(request);

        assertNotNull(result);
        assertNotNull(sourceCurrency);
        assertEquals("EUR", result.getSourceCurrency());
        assertEquals("NGN", result.getTargetCurrency());
        assertEquals(BigDecimal.valueOf(10), result.getSourceAmount());
        assertEquals(BigDecimal.valueOf(493.06), result.getTargetAmount()); // Assuming ExchangeRate.nairaEuro = 493.06

    }


    @Test
    public void testConvertCurrency_USDToUGX() {
        CurrencyRequest request = new CurrencyRequest();
        request.setCountry("United State");
        request.setAmount(BigDecimal.valueOf(100));
        request.setTargetCurrency("UGX");

        CountryCurrencyResponse countryCurrencyResponse = new CountryCurrencyResponse();
        countryCurrencyResponse.getData().setCurrency("USD");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        CountryCurrencyRequest currencyRequest = new CountryCurrencyRequest();
        currencyRequest.setCountry(request.getCountry());
        HttpEntity<CountryCurrencyRequest> entity = new HttpEntity<>(currencyRequest, headers);
        ResponseEntity<CountryCurrencyResponse> currencyResponse = restTemplate.exchange(countryCurrencyApi, HttpMethod.POST, entity, CountryCurrencyResponse.class);
        String sourceCurrency = currencyResponse.getBody().getData().getCurrency();


        ConvertedCurrencyResponse result = countryCurrencyService.convertCurrency(request);

        assertNotNull(result);
        assertNotNull(sourceCurrency);
        assertEquals("USD", result.getSourceCurrency());
        assertEquals("UGX", result.getTargetCurrency());
        assertEquals(BigDecimal.valueOf(50), result.getSourceAmount());
        assertEquals(BigDecimal.valueOf(150), result.getTargetAmount()); // Assuming ExchangeRate.nairaEuro = 3

    }
}
