package com.example.KlashaBE.data;

import com.example.KlashaBE.data.State;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CityData {
    private String name;
    private String iso2;
    private String iso3;
    private List<State> state;
}
