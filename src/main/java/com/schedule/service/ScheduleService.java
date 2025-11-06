package com.schedule.service;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.DeleteScheduleRequest;
import com.schedule.dto.UpdateScheduleRequest;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.dto.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getAll(String author) {
        List<Schedule> list = (author == null || author.isBlank())
                ? repository.findAllByOrderByModifiedAtDesc()
                : repository.findAllByAuthorOrderByModifiedAtDesc(author);
        return list.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponse getById(Long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다. id=" + id));
        return toResponse(schedule);
    }

    @Transactional
    public ScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule schedule = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일정을 찾을 수 없습니다. id=" + id)
        );

        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀 번호가 일치하지 않습니다.");
        }
        if (request.getTitle() != null) schedule.setTitle(request.getTitle());
        if (request.getAuthor() != null) schedule.setAuthor(request.getAuthor());

        Schedule updated = repository.save(schedule);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id, DeleteScheduleRequest request) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다. id=" + id));

        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        repository.deleteById(id);
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
