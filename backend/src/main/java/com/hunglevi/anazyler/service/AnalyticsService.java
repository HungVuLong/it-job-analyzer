package com.hunglevi.anazyler.service;

import com.hunglevi.anazyler.dto.*;
import com.hunglevi.anazyler.repository.AnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public List<SkillCountDto> topSkills(String country, int limit) {
        return analyticsRepository.topSkills(country, limit);
    }

    public List<SalaryBySkillDto> salaryBySkill(String country, int limit) {
        return analyticsRepository.salaryBySkill(country, limit);
    }

    public List<SalaryBucketDto> salaryDistribution(String country) {
        return analyticsRepository.salaryDistribution(country);
    }
}
