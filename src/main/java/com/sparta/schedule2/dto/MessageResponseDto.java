package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long scheduleId;

    public MessageResponseDto(Message Message) {
        this.id = Message.getId();
        this.username = Message.getUsername();
        this.content = Message.getContent();
        this.createdAt = Message.getCreatedAt();
        this.modifiedAt = Message.getModifiedAt();
        this.scheduleId = Message.getSchedule().getId();
    }
}
