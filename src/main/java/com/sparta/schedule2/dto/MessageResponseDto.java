package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//생성, 응답된 댓글 정보
@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long scheduleId;

    public MessageResponseDto(Message Message) {
        this.id = Message.getId();
        this.userId = Message.getUser().getId();
        this.content = Message.getContent();
        this.createdAt = Message.getCreatedAt();
        this.modifiedAt = Message.getModifiedAt();
        this.scheduleId = Message.getSchedule().getId();
    }
}
