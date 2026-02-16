package com.hunglevi.anazyler.dto;

import java.time.LocalDate;

public interface JobSummaryDto {
    Long getId();
    String getTitle();
    Long getSalaryMinVnd();
    Long getSalaryMaxVnd();
    String getCurrencyOriginal();
    String getExperienceLevel();
    LocalDate getPostedDate();
    String getCity();
    String getCountry();
    String getCompanyName();
}
