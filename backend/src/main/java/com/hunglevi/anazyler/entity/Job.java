package com.hunglevi.anazyler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs", schema = "core")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // traceability về raw job
    @Column(name = "raw_job_id")
    private Long rawJobId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "salary_min_vnd")
    private Long salaryMinVnd;

    @Column(name = "salary_max_vnd")
    private Long salaryMaxVnd;

    @Column(name = "currency_original", length = 10)
    private String currencyOriginal;

    @Column(name = "salary_raw", length = 150)
    private String salaryRaw;

    @Column(name = "experience_level", length = 50)
    private String experienceLevel;

    @Column(name = "posted_date")
    private LocalDate postedDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
