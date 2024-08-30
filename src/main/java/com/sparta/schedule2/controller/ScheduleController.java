package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.schedule.request.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.schedule.response.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.schedule.response.ScheduleResponseDto;
import com.sparta.schedule2.dto.schedule.request.ScheduleUpdateRequestDto;
import com.sparta.schedule2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//일정 관련 페이지
@RestController
@RequestMapping("/api/schedule")
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 일정 생성
     *
     * @Param 일정 등록 정보
     * @Return 생성된 일정 정보
     */
    @PostMapping("/create")
    public ScheduleDetailResponseDto createSchedule(@Valid @RequestBody ScheduleCreateRequestDto requestDto) {
        log.info(requestDto.getTitle());
        return scheduleService.createSchedule(requestDto);
    }

    /**
     * 일정 단건 조회
     *
     * @Param 조회할 일정 아이디
     * @Return 조회된 일정 정보
     */
    @GetMapping("/{scheduleId}")
    public ScheduleDetailResponseDto getSchedule(@PathVariable final Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    /**
     * 일정 목록
     *
     * @Param 페이지의 인덱스와 각 페이지의 크기
     * @Return 생성된 일정 정보
     */
    @GetMapping("")
    public ResponseEntity<Page<ScheduleResponseDto>> getAllSchedules(@RequestParam(defaultValue = "1", required = false) int page,
                                                                     @RequestParam(defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(scheduleService.getSchedules(page, size));

    }

    /**
     * 일정 수정
     *
     * @Param 수정할 일정 아이디, 수정 정보
     * @Return 수정된 일정 정보
     */
    @PutMapping("/{scheduleId}")
    public ScheduleDetailResponseDto updateSchedule(@PathVariable final Long scheduleId, @Valid @RequestBody final ScheduleUpdateRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto);
    }

    /**
     * 일정 삭제
     *
     * @Param 삭제할 일정 아이디
     * @Return
     */
    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable final Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }
}
