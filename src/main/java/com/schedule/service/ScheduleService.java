package com.schedule.service;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.dto.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;

    @Transactional
    public ScheduleResponse create(CreateScheduleRequest reqeust) {
        Schedule schedule = Schedule.builder()
                .title(reqeust.getTitle())
                .content(reqeust.getContent())
                .author(reqeust.getAuthor())
                .password(reqeust.getPassword())
                .build();
        Schedule saved = repository.save(schedule);
        return toResponse(saved);
    }

    private ScheduleResponse toResponse(Schedule schedule) {
        return ScheduleResponse.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .author(schedule.getAuthor())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }
}
