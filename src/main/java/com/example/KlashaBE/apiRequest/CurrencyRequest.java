package com.example.KlashaBE.apiRequest;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class CurrencyRequest {
    private String country;
    private BigDecimal amount;
    private String targetCurrency;
}
