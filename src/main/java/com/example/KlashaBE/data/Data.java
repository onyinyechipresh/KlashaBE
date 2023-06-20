package com.example.KlashaBE.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private String city;
    private String country;
    private List<PopulationCount> populationCount;
}
