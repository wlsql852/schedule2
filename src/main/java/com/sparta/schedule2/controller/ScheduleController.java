package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        return scheduleService.cteateSchedule(requestDto);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable final Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable final Long scheduleId, @RequestBody final ScheduleUpdateRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto);
    }


}
