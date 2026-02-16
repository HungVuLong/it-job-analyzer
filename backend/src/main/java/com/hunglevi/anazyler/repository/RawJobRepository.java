package com.hunglevi.anazyler.repository;

import com.hunglevi.anazyler.dto.RawJobStatusCountDto;
import com.hunglevi.anazyler.entity.staging.RawJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RawJobRepository extends JpaRepository<RawJob, Long> {

    @Query(value = """
      SELECT status AS status, COUNT(*) AS count
      FROM staging.raw_jobs
      GROUP BY status
      """, nativeQuery = true)
    List<RawJobStatusCountDto> countByStatus();

    @Query(value = """
      SELECT COUNT(*) FROM staging.raw_jobs
      WHERE status = :status
      """, nativeQuery = true)
    long countByStatus(@Param("status") String status);
}
