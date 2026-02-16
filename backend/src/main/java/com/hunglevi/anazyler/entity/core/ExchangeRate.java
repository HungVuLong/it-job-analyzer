package com.hunglevi.anazyler.entity.core;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rates", schema = "core")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@IdClass(ExchangeRateKey.class)
public class ExchangeRate {

    @Id
    private LocalDate date;

    @Id
    @Column(name = "currency_from", length = 10)
    private String currencyFrom;

    @Id
    @Column(name = "currency_to", length = 10)
    private String currencyTo;

    @Column(nullable = false, precision = 15, scale = 6)
    private BigDecimal rate;
}
