package com.sparta.schedule2.dto;

import lombok.Getter;

//댓글 생성 정보
@Getter
public class MessageCreateRequestDto {
    private Long scheduleId;
    private Long userId;
    private String content;
}
