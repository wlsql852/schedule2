package com.sparta.schedule2.dto.schedule.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

//일정 수정 정보
@Getter
public class ScheduleUpdateRequestDto {
    @NotBlank(message = "제목을 필수로 입력되어야 합니다.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;
}
