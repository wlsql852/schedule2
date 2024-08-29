package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.Manage;
import com.sparta.schedule2.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleDetailResponseDto {
    private Long id;
    private UserSimpleResponseDto createdBy;
    private String title;
    private String content;
    private List<UserSimpleResponseDto> managers;
    private int messageCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleDetailResponseDto(Schedule schedule, List<Manage> managers) {
        this.id = schedule.getId();
        this.createdBy = new UserSimpleResponseDto(schedule.getCreatedBy());
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.messageCount = schedule.getMessages().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.managers = managers.stream().map(Manage::getManager).map(UserSimpleResponseDto::new).toList();
    }


    public ScheduleDetailResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.createdBy = new UserSimpleResponseDto(schedule.getCreatedBy());
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.messageCount = schedule.getMessages().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.managers = schedule.getManageList().stream().map(manage -> new UserSimpleResponseDto(manage.getManager())).toList();
    }
}
