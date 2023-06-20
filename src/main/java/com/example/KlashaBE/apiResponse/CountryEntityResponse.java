package com.example.KlashaBE.apiResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryEntityResponse {
    private CountryCapitalCityResponse capital;
    private CountryIsoResponse iso;
    private CountryLocationResponse location;
    private CountryPopulationResponse population;
    private CountryCurrencyResponse currency;

}
