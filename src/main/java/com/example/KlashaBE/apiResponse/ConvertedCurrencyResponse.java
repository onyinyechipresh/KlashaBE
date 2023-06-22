package com.example.KlashaBE.apiResponse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConvertedCurrencyResponse {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
}
