package com.schedule.controller;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.DeleteScheduleRequest;
import com.schedule.dto.ScheduleResponse;
import com.schedule.dto.UpdateScheduleRequest;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    // 속성
    private final ScheduleService service;

    //생성자

    //기능
    @PostMapping
    public ResponseEntity<ScheduleResponse> create(@RequestBody CreateScheduleRequest request) {
        ScheduleResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll(@RequestParam(value = "author", required = false) String author) {
        return ResponseEntity.ok(service.getAll(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable Long id, @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteScheduleRequest request) {
        service.delete(id, request);
        return ResponseEntity.noContent().build();
    }
}
