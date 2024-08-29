package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private UserSimpleResponseDto createdBy;
    private String title;
    private String content;
    private int messageCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.createdBy = new UserSimpleResponseDto(schedule.getCreatedBy());
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.messageCount = schedule.getMessages().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }


}
