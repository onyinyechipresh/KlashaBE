package com.example.KlashaBE.apiResponse;

import com.example.KlashaBE.data.CountryLocationData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryLocationResponse {
    private String error;
    private String msg;
    private CountryLocationData data;
}
