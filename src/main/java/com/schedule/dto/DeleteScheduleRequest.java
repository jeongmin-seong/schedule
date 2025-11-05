package com.schedule.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteScheduleRequest {
    private String password;
}
