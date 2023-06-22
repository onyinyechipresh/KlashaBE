package com.example.KlashaBE;

import com.example.KlashaBE.apiRequest.DataRequest;
import com.example.KlashaBE.apiResponse.BaseResponse;
import com.example.KlashaBE.apiResponse.DataResponse;
import com.example.KlashaBE.services.serviceImplementation.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class CityServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    @InjectMocks
    private CityServiceImpl cityService;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Value("${all.cities.endpoint:https://countriesnow.space/api/v0.1/countries/population/cities/filter}")
    private String allCityApi;

    @Test
    public ResponseEntity<DataResponse> testGetAllCities() {
        long limit = 10L;

        DataRequest ghanaRequest = new DataRequest();
        ghanaRequest.setCountry("ghana");
        ghanaRequest.setOrder("dsc");
        ghanaRequest.setOrderBy("value");
        ghanaRequest.setLimit(limit);

        DataResponse ghanaResponse = new DataResponse();
        ghanaResponse.setError(false);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        DataRequest request = new DataRequest();
        request.setCountry("Ghana");
        request.setOrder("dsc");
        request.setOrderBy("value");
        request.setLimit(limit);
        HttpEntity<DataRequest> ghanaEntity = new HttpEntity<>(request,headers);

        ResponseEntity<DataResponse> ghanaResponseEntity = restTemplate.exchange(allCityApi, HttpMethod.POST, ghanaEntity, DataResponse.class);

        BaseResponse result = cityService.getAllCities((long) 5.0);

        // Assert
        assertNotNull(result);
        assertFalse(result.getGhana().isError());
        assertNull(result.getItaly());
        assertNull(result.getNewZealand());
        return ghanaResponseEntity;

    }
}
