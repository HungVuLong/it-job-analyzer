package com.hunglevi.anazyler.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ExchangeRateKey implements Serializable {
    private LocalDate date;
    private String currencyFrom;
    private String currencyTo;
}
