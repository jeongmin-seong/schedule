package com.schedule.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateScheduleRequest {
    private String title;
    private String content;
    private String author;
    private String password;
}
