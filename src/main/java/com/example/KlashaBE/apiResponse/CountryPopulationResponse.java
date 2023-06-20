package com.example.KlashaBE.apiResponse;

import com.example.KlashaBE.data.CountryPopulationData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryPopulationResponse {
    private String error;
    private String msg;
    private CountryPopulationData data;
}
