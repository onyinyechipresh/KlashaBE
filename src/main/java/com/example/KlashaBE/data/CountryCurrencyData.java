package com.example.KlashaBE.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CountryCurrencyData {
    private String name;
    private String currency;
    private String iso2;
    private String iso3;
}
