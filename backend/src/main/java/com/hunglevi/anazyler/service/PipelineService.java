package com.hunglevi.anazyler.service;

import com.hunglevi.anazyler.dto.RawJobStatusCountDto;
import com.hunglevi.anazyler.repository.RawJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PipelineService {

    private final RawJobRepository rawJobRepository;

    public List<RawJobStatusCountDto> rawJobStatusSummary() {
        return rawJobRepository.countByStatus();
    }

    public long countByStatus(String status) {
        return rawJobRepository.countByStatus(status);
    }
}
