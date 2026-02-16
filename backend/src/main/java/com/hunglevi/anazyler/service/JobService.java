package com.hunglevi.anazyler.service;

import com.hunglevi.anazyler.dto.JobSummaryDto;
import com.hunglevi.anazyler.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Page<JobSummaryDto> searchJobs(
            String country,
            String skill,
            String keyword,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.desc("postedDate"), Sort.Order.desc("id"))
        );

        return jobRepository.searchJobs(country, skill, keyword, pageable);
    }

    public JobSummaryDto getJobSummary(Long jobId) {
        return jobRepository.getJobSummary(jobId);
    }
}
