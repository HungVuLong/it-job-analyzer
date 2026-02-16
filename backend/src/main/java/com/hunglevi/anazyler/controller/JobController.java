package com.hunglevi.anazyler.controller;

import com.hunglevi.anazyler.dto.JobSummaryDto;
import com.hunglevi.anazyler.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public Page<JobSummaryDto> searchJobs(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return jobService.searchJobs(country, skill, keyword, page, size);
    }

    @GetMapping("/{id}")
    public JobSummaryDto getJob(@PathVariable Long id) {
        return jobService.getJobSummary(id);
    }
}
