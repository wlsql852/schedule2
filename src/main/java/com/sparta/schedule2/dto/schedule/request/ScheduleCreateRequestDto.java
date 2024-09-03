package com.sparta.schedule2.dto.schedule.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

//일정 생성 정보
@Getter
public class ScheduleCreateRequestDto {
    @NotNull(message = "생성자 아이디가 필수로 입력되어야 합니다")
    private Long createdBy;
    @NotBlank(message = "제목을 필수로 입력되어야 합니다.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;
}
