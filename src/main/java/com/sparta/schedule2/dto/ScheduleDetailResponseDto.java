package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.Manage;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleDetailResponseDto {
    private Long id;
    private UserResponseDto createdBy;
    private String title;
    private String content;
    private List<UserResponseDto> managers;
    private int messageCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleDetailResponseDto(Schedule schedule, List<Manage> managers) {
        this.id = schedule.getId();
        this.createdBy = new UserResponseDto(schedule.getCreatedBy());
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.messageCount = schedule.getMessages().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.managers = managers.stream().map(manage -> manage.getManager()).map(manager-> new UserResponseDto(manager)).toList();
    }


    public ScheduleDetailResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.createdBy = new UserResponseDto(schedule.getCreatedBy());
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.messageCount = schedule.getMessages().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.managers = schedule.getManageList().stream().map(manage -> new UserResponseDto(manage.getManager())).toList();
    }
}
