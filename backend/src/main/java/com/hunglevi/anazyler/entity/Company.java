package com.hunglevi.anazyler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies", schema = "core",
       uniqueConstraints = @UniqueConstraint(name = "uk_companies_name", columnNames = "name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    private String website;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "origin_country", length = 10)
    private String originCountry;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
