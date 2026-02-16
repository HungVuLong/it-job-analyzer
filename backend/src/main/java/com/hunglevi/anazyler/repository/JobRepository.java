package com.hunglevi.anazyler.repository;

import com.hunglevi.anazyler.dto.JobSummaryDto;
import com.hunglevi.anazyler.entity.core.Job;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(value = """
      SELECT
        j.id                         AS id,
        j.title                      AS title,
        j.salary_min_vnd             AS salaryMinVnd,
        j.salary_max_vnd             AS salaryMaxVnd,
        j.currency_original          AS currencyOriginal,
        j.experience_level           AS experienceLevel,
        j.posted_date                AS postedDate,
        l.city                       AS city,
        l.country                    AS country,
        c.name                       AS companyName
      FROM core.jobs j
      LEFT JOIN core.locations l ON j.location_id = l.id
      LEFT JOIN core.companies c ON j.company_id = c.id
      WHERE (:country IS NULL OR l.country = :country)
        AND (:keyword IS NULL OR lower(j.title) LIKE lower(concat('%', :keyword, '%')))
        AND (:skill IS NULL OR EXISTS (
              SELECT 1
              FROM core.job_skills js
              JOIN core.skills s ON js.skill_id = s.id
              WHERE js.job_id = j.id AND lower(s.name) = lower(:skill)
        ))
      ORDER BY j.posted_date DESC NULLS LAST, j.id DESC
      """,
      countQuery = """
      SELECT COUNT(*)
      FROM core.jobs j
      LEFT JOIN core.locations l ON j.location_id = l.id
      WHERE (:country IS NULL OR l.country = :country)
        AND (:keyword IS NULL OR lower(j.title) LIKE lower(concat('%', :keyword, '%')))
        AND (:skill IS NULL OR EXISTS (
              SELECT 1
              FROM core.job_skills js
              JOIN core.skills s ON js.skill_id = s.id
              WHERE js.job_id = j.id AND lower(s.name) = lower(:skill)
        ))
      """,
      nativeQuery = true)
    Page<JobSummaryDto> searchJobs(
            @Param("country") String country,
            @Param("skill") String skill,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    @Query(value = """
      SELECT
        j.id                         AS id,
        j.title                      AS title,
        j.salary_min_vnd             AS salaryMinVnd,
        j.salary_max_vnd             AS salaryMaxVnd,
        j.currency_original          AS currencyOriginal,
        j.experience_level           AS experienceLevel,
        j.posted_date                AS postedDate,
        l.city                       AS city,
        l.country                    AS country,
        c.name                       AS companyName
      FROM core.jobs j
      LEFT JOIN core.locations l ON j.location_id = l.id
      LEFT JOIN core.companies c ON j.company_id = c.id
      WHERE j.id = :jobId
      """, nativeQuery = true)
    JobSummaryDto getJobSummary(@Param("jobId") Long jobId);
}
