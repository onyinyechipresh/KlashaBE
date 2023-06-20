package com.example.KlashaBE.apiResponse;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CityCountryBaseResponse {
    private String country;
    private Map<String, List<String>> state;
}
