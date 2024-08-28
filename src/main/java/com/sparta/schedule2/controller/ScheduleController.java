package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<Page<ScheduleResponseDto>> getAllSchedules(@RequestParam(defaultValue = "1", required = false) int page,
                                                                     @RequestParam(defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(scheduleService.getSchedules(page, size));

    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable final Long scheduleId, @RequestBody final ScheduleUpdateRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable final Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }


}
