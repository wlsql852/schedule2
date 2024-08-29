package com.sparta.schedule2.dto;

import lombok.Getter;

import java.util.List;

//일정 수정 정보
@Getter
public class ScheduleUpdateRequestDto {
    private String title;
    private String content;
    private List<Long> managers;
}
