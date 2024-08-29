package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ScheduleDetailResponseDto createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        return scheduleService.cteateSchedule(requestDto);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleDetailResponseDto getSchedule(@PathVariable final Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("")
    public ResponseEntity<Page<ScheduleResponseDto>> getAllSchedules(@RequestParam(defaultValue = "1", required = false) int page,
                                                                     @RequestParam(defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(scheduleService.getSchedules(page, size));

    }

    @PutMapping("/{scheduleId}")
    public ScheduleDetailResponseDto updateSchedule(@PathVariable final Long scheduleId, @RequestBody final ScheduleUpdateRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable final Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }


}
