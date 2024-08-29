package com.sparta.schedule2.dto;

import lombok.Getter;

@Getter
public class MessageCreateRequestDto {
    private Long scheduleId;
    private Long userId;
    private String content;
}
