package com.schedule.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateScheduleRequest {
    private String title;
    private String author;
    private String password;
}
