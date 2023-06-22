package com.example.KlashaBE.services.serviceImplementation;

import com.example.KlashaBE.apiRequest.DataRequest;
import com.example.KlashaBE.apiResponse.BaseResponse;
import com.example.KlashaBE.apiResponse.DataResponse;
import com.example.KlashaBE.exception.CustomAppException;
import com.example.KlashaBE.services.CityService;
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
public class CityServiceImpl implements CityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;

    @Value("${all.cities.endpoint:https://countriesnow.space/api/v0.1/countries/population/cities/filter}")
    private String allCityApi;

    @Override
    public BaseResponse getAllCities(Long limit){
        try {
            HttpEntity<DataRequest> ghanaEntity = buildRequest("ghana", limit);
            ResponseEntity<DataResponse> ghanaResponse = restTemplate.exchange(allCityApi, HttpMethod.POST, ghanaEntity, DataResponse.class);
            log.info("stateResponse {}",ghanaResponse);
       if(ghanaResponse.getBody().isError()){
           throw new CustomAppException("Error");
       }

            HttpEntity<DataRequest> italyEntity = buildRequest("italy", limit);
            ResponseEntity<DataResponse> italyResponse = restTemplate.exchange(allCityApi, HttpMethod.POST, italyEntity, DataResponse.class);
        if(italyResponse.getBody().isError()){
            throw new CustomAppException("Error");
        }

            HttpEntity<DataRequest> newZealandEntity = buildRequest("New Zealand", limit);
            ResponseEntity<DataResponse> newZealandResponse = restTemplate.exchange(allCityApi, HttpMethod.POST, newZealandEntity, DataResponse.class);
        if(newZealandResponse.getBody().isError()){
            throw new CustomAppException("Error");
        }
        Thread.sleep(5);
            return BaseResponse.builder()
                    .ghana(ghanaResponse.getBody())
                    .italy(italyResponse.getBody())
                    .newZealand(newZealandResponse.getBody())
                    .build();
        }catch (Exception e){
            logger.error("error fetching data - {} ", e.getMessage());
        }
        return new BaseResponse();
    }

    private HttpEntity<DataRequest> buildRequest(String country,Long limit){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        DataRequest request = new DataRequest();
        request.setCountry(country);
        request.setOrder("dsc");
        request.setOrderBy("value");
        request.setLimit(limit);
        HttpEntity<DataRequest> entity = new HttpEntity<>(request,headers);
        return entity;
    }
}
