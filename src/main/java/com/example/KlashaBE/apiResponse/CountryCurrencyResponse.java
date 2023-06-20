package com.example.KlashaBE.apiResponse;

import com.example.KlashaBE.data.CountryCurrencyData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryCurrencyResponse {
    private String error;
    private String msg;
    private CountryCurrencyData data;
}
