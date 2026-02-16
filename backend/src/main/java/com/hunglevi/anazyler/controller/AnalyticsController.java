package com.hunglevi.anazyler.controller;

import com.hunglevi.anazyler.dto.*;
import com.hunglevi.anazyler.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/top-skills")
    public List<SkillCountDto> topSkills(
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return analyticsService.topSkills(country, limit);
    }

    @GetMapping("/salary-by-skill")
    public List<SalaryBySkillDto> salaryBySkill(
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return analyticsService.salaryBySkill(country, limit);
    }

    @GetMapping("/salary-distribution")
    public List<SalaryBucketDto> salaryDistribution(
            @RequestParam(required = false) String country
    ) {
        return analyticsService.salaryDistribution(country);
    }
}
