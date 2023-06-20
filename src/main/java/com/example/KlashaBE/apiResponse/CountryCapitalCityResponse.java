package com.example.KlashaBE.apiResponse;

import com.example.KlashaBE.data.CapitalCityData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryCapitalCityResponse {
    private String error;
    private String msg;
    private CapitalCityData data;
}
