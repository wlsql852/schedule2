package com.sparta.schedule2.dto.message.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

//댓글 생성 정보
@Getter
public class MessageCreateRequestDto {
    @NotNull(message = "일정이 필수로 입력되어야 합니다")
    private Long scheduleId;
    @NotNull(message = "생성자 아이디가 필수로 입력되어야 합니다")
    private Long userId;
    @NotEmpty(message = "내용을 넣어주세요")
    @Size(max=500, message = "500자 이하만 가능합니다.")
    private String content;
}
