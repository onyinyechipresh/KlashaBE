package com.example.KlashaBE.services.serviceImplementation;

import com.example.KlashaBE.apiRequest.CountryCurrencyRequest;
import com.example.KlashaBE.apiRequest.CurrencyRequest;
import com.example.KlashaBE.data.ExchangeRate;
import com.example.KlashaBE.apiResponse.ConvertedCurrencyResponse;
import com.example.KlashaBE.apiResponse.CountryCurrencyResponse;
import com.example.KlashaBE.services.CountryCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@RequiredArgsConstructor
@Slf4j
public class CountryCurrencyServiceImpl implements CountryCurrencyService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;
    @Value("${country.currency.endpoint:https://countriesnow.space/api/v0.1/countries/currency}")
    private String countryCurrencyApi;
    public ConvertedCurrencyResponse convertCurrency(CurrencyRequest request){
        String targetCurrency = request.getTargetCurrency();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            CountryCurrencyRequest currencyRequest = new CountryCurrencyRequest();
            currencyRequest.setCountry(request.getCountry());
            HttpEntity<CountryCurrencyRequest> entity = new HttpEntity<>(currencyRequest, headers);
            ResponseEntity<CountryCurrencyResponse> currencyResponse = restTemplate.exchange(countryCurrencyApi, HttpMethod.POST, entity, CountryCurrencyResponse.class);
            String sourceCurrency = currencyResponse.getBody().getData().getCurrency();

            ConvertedCurrencyResponse convertedCurrencyResponse = new ConvertedCurrencyResponse();
            if (sourceCurrency.equals("EUR") && targetCurrency.equals("NGN")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.nairaEuro));

                log.info("convertedCurrencyResponse {}", convertedCurrencyResponse);
                return convertedCurrencyResponse;
            } else if (sourceCurrency.equals("USD") && targetCurrency.equals("NGN")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.nairaUsd));
                return convertedCurrencyResponse;
            } else if (sourceCurrency.equals("JPY") && targetCurrency.equals("NGN")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.nairaJpy));
                return convertedCurrencyResponse;
            } else if (sourceCurrency.equals("GPB") && targetCurrency.equals("NGN")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.nairaGpb));
                return convertedCurrencyResponse;
            }

            if (sourceCurrency.equals("EUR") && targetCurrency.equals("UGX")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.ugxEuro));
                return convertedCurrencyResponse;
            } else if (sourceCurrency.equals("USD") && targetCurrency.equals("UGX")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.ugxUsd));
                return convertedCurrencyResponse;
            } else if (sourceCurrency.equals("JPY") && targetCurrency.equals("UGX")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.ugxJpy));
                return convertedCurrencyResponse;
            } else if (sourceCurrency.equals("GBP") && targetCurrency.equals("UGX")) {
                convertedCurrencyResponse.setSourceCurrency(sourceCurrency);
                convertedCurrencyResponse.setTargetCurrency(targetCurrency);
                convertedCurrencyResponse.setSourceAmount(request.getAmount());
                convertedCurrencyResponse.setTargetAmount(request.getAmount().multiply(ExchangeRate.ugxGbp));
                return convertedCurrencyResponse;
            }

        }catch (Exception e){
            logger.error("error fetching data - {} ", e.getMessage());
        }
        return new ConvertedCurrencyResponse();
    }
}
