package com.sparta.schedule2.dto.schedule.response;

import com.sparta.schedule2.dto.user.response.UserSimpleResponseDto;
import com.sparta.schedule2.entity.Manage;
import com.sparta.schedule2.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// 담당자 정보가 들어간 응답정보
//단건조회, 생성, 수정시 사용
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

    //일정 생성/단건조회
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


    //일정 수정
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
