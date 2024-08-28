package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto cteateSchedule(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        System.out.println(saveSchedule.getUsername());
        ScheduleResponseDto responseDto = new ScheduleResponseDto(saveSchedule);
        System.out.println(saveSchedule.getId());
        System.out.println(responseDto.getContent());
        return responseDto;
    }

    public ScheduleResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        Schedule saveSchedule = schedule.update(requestDto);
        scheduleRepository.save(saveSchedule);
        return new ScheduleResponseDto(saveSchedule);

    }
}
