package com.schedule.dto;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateScheduleRequest {
    private String title;
    private String author;
    private String password;
}
