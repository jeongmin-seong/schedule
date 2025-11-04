package com.schedule.controller;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.ScheduleResponse;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping
    public ResponseEntity<ScheduleResponse> create(@RequestBody CreateScheduleRequest request) {
        ScheduleResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }
}
