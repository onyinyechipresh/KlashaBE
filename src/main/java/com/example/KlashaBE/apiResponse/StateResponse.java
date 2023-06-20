package com.example.KlashaBE.apiResponse;

import com.example.KlashaBE.data.CityData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class StateResponse {
    private String error;
    private String msg;
    private CityData data;
}
