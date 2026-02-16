package com.hunglevi.anazyler.controller;

import com.hunglevi.anazyler.dto.RawJobStatusCountDto;
import com.hunglevi.anazyler.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pipeline")
@RequiredArgsConstructor
public class PipelineController {

    private final PipelineService pipelineService;

    @GetMapping("/raw-status")
    public List<RawJobStatusCountDto> rawStatus() {
        return pipelineService.rawJobStatusSummary();
    }
}
