package com.hunglevi.anazyler.entity.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills", schema = "core",
       uniqueConstraints = @UniqueConstraint(name = "uk_skills_name", columnNames = "name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String category;

    @Column(columnDefinition = "text")
    private String aliases;
}
