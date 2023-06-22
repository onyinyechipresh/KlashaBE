package com.example.KlashaBE.services.serviceImplementation;

import com.example.KlashaBE.apiRequest.CountryStateRequest;
import com.example.KlashaBE.apiResponse.CityCountryBaseResponse;
import com.example.KlashaBE.apiResponse.CountryStateResponse;
import com.example.KlashaBE.apiRequest.StateRequest;
import com.example.KlashaBE.data.State;
import com.example.KlashaBE.apiResponse.StateResponse;
import com.example.KlashaBE.services.StateService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StateServiceImpl implements StateService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;
    @Value("${all.states.endpoint:https://countriesnow.space/api/v0.1/countries/states}")
    private String allStateApi;

    @Value("${all.cities.in.state.endpoint:https://countriesnow.space/api/v0.1/countries/state/cities}")
    private String allCitiesApi;


    public CityCountryBaseResponse getAllStatesByCountry(StateRequest country){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            StateRequest request = new StateRequest();
            request.setCountry(country.getCountry());
            HttpEntity<StateRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<StateResponse> stateResponse = restTemplate.exchange(allStateApi, HttpMethod.POST, entity, StateResponse.class);
            System.out.println("StateResponse : : " + stateResponse);
            log.info("stateResponse {}", stateResponse);

            CityCountryBaseResponse cityCountryBaseResponse = new CityCountryBaseResponse();
            cityCountryBaseResponse.setCountry(country.getCountry());

            Map<String, List<String>> stateCity = new HashMap<>();

            List<State> states = stateResponse.getBody().getData().getState();
            for (var state : states) {
                stateCity.put(state.getName(), getAllCityByState(country.getCountry(), state.getName()));
            }
            cityCountryBaseResponse.setState(stateCity);
            return cityCountryBaseResponse;
        }catch(Exception e){
            logger.error("error fetching data - {} ", e.getMessage());
        }
        return new CityCountryBaseResponse();
    }

    public List<String> getAllCityByState(String country, String state){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        CountryStateRequest request = new CountryStateRequest();
        request.setCountry(country);
        request.setState(state);
        HttpEntity<CountryStateRequest> entity = new HttpEntity<>(request,headers);

        ResponseEntity<CountryStateResponse> countryStateResponse = restTemplate.exchange(allCitiesApi,HttpMethod.POST,entity,CountryStateResponse.class);
        return countryStateResponse.getBody().getData();
    }
}
