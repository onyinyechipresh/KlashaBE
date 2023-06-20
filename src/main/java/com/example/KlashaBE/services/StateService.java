package com.example.KlashaBE.services;

import com.example.KlashaBE.apiRequest.StateRequest;
import com.example.KlashaBE.apiResponse.CityCountryBaseResponse;

public interface StateService {
    CityCountryBaseResponse getAllStatesByCountry(StateRequest country);
}
