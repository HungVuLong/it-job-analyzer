package com.hunglevi.anazyler.entity.staging;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "raw_jobs", schema = "staging",
       uniqueConstraints = @UniqueConstraint(name = "uk_raw_jobs_url", columnNames = "job_url"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RawJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_site", nullable = false, length = 50)
    private String sourceSite;

    @Column(name = "job_url", nullable = false, length = 500)
    private String jobUrl;

    // jsonb: có thể dùng Map<String,Object> thay vì String nếu muốn
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_data", nullable = false, columnDefinition = "jsonb")
    private String rawData;

    @Column(name = "crawled_at", nullable = false)
    private LocalDateTime crawledAt;

    @Column(nullable = false, length = 20)
    private String status; // PENDING/PROCESSED/ERROR

    @Column(name = "error_message", columnDefinition = "text")
    private String errorMessage;
}
