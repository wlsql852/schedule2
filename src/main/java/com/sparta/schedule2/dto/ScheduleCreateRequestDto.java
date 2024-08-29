package com.sparta.schedule2.dto;

import lombok.Getter;

import java.util.List;

//일정 생성 정보
@Getter
public class ScheduleCreateRequestDto {
    private Long createdBy;
    private List<Long> managers;
    private String title;
    private String content;
}
