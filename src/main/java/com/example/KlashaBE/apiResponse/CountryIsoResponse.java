package com.example.KlashaBE.apiResponse;

import com.example.KlashaBE.data.CountryIsoData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryIsoResponse {
    private String error;
    private String msg;
    private CountryIsoData data;
}
