package com.hunglevi.anazyler.repository;

import com.hunglevi.anazyler.dto.*;
import com.hunglevi.anazyler.entity.core.Skill;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalyticsRepository extends JpaRepository<Skill, Integer> {

    @Query(value = """
      SELECT s.name AS skill, COUNT(*) AS jobCount
      FROM core.job_skills js
      JOIN core.skills s ON js.skill_id = s.id
      JOIN core.jobs j ON js.job_id = j.id
      JOIN core.locations l ON j.location_id = l.id
      WHERE (:country IS NULL OR l.country = :country)
      GROUP BY s.name
      ORDER BY jobCount DESC
      LIMIT :limit
      """, nativeQuery = true)
    List<SkillCountDto> topSkills(@Param("country") String country, @Param("limit") int limit);

    @Query(value = """
      SELECT
        s.name AS skill,
        COUNT(*) AS jobCount,
        AVG(
          (
            COALESCE(j.salary_min_vnd, j.salary_max_vnd) +
            COALESCE(j.salary_max_vnd, j.salary_min_vnd)
          ) / 2.0
        ) AS avgSalaryVnd
      FROM core.job_skills js
      JOIN core.skills s ON js.skill_id = s.id
      JOIN core.jobs j ON js.job_id = j.id
      JOIN core.locations l ON j.location_id = l.id
      WHERE (:country IS NULL OR l.country = :country)
        AND (j.salary_min_vnd IS NOT NULL OR j.salary_max_vnd IS NOT NULL)
      GROUP BY s.name
      ORDER BY avgSalaryVnd DESC NULLS LAST
      LIMIT :limit
      """, nativeQuery = true)
    List<SalaryBySkillDto> salaryBySkill(@Param("country") String country, @Param("limit") int limit);

    /**
     * Optional: histogram buckets để vẽ chart phân bố lương.
     * Buckets dạng: <20m, 20-40m, 40-60m, 60-80m, 80m+
     */
    @Query(value = """
      WITH base AS (
        SELECT
          ((COALESCE(j.salary_min_vnd, j.salary_max_vnd) +
            COALESCE(j.salary_max_vnd, j.salary_min_vnd)) / 2) AS mid_salary
        FROM core.jobs j
        JOIN core.locations l ON j.location_id = l.id
        WHERE (:country IS NULL OR l.country = :country)
          AND (j.salary_min_vnd IS NOT NULL OR j.salary_max_vnd IS NOT NULL)
      )
      SELECT
        CASE
          WHEN mid_salary < 20000000 THEN '<20m'
          WHEN mid_salary < 40000000 THEN '20-40m'
          WHEN mid_salary < 60000000 THEN '40-60m'
          WHEN mid_salary < 80000000 THEN '60-80m'
          ELSE '80m+'
        END AS bucket,
        COUNT(*) AS jobCount
      FROM base
      GROUP BY bucket
      ORDER BY
        CASE bucket
          WHEN '<20m' THEN 1
          WHEN '20-40m' THEN 2
          WHEN '40-60m' THEN 3
          WHEN '60-80m' THEN 4
          ELSE 5
        END
      """, nativeQuery = true)
    List<SalaryBucketDto> salaryDistribution(@Param("country") String country);
}
