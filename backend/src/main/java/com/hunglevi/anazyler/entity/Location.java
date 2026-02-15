package com.hunglevi.anazyler.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations", schema = "core",
       uniqueConstraints = @UniqueConstraint(name = "uk_locations", columnNames = {"city", "country"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 10)
    private String country; // VN / JP
}
