package com.sparta.schedule2.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleCreateRequestDto {
    private Long createdBy;
    private List<Long> managers;
    private String title;
    private String content;
}
