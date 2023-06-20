package com.example.KlashaBE.data;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryPopulationData {
    private String country;
    private String code;
    private String iso3;
    private List<PopulationCounts> populationCounts;
}
