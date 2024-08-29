package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//일정 관련 페이지
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 생성
     *
     * @Param 일정 등록 정보
     * @Return 생성된 일정 정보
     */
    @PostMapping("/create")
    public ScheduleDetailResponseDto createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        return scheduleService.cteateSchedule(requestDto);
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
    public ScheduleDetailResponseDto updateSchedule(@PathVariable final Long scheduleId, @RequestBody final ScheduleUpdateRequestDto requestDto) {
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
