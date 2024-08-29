package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.entity.Manage;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.ManageRepository;
import com.sparta.schedule2.repository.ScheduleRepository;
import com.sparta.schedule2.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ManageRepository manageRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository, ManageRepository manageRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.manageRepository = manageRepository;
    }

    public ScheduleDetailResponseDto cteateSchedule(ScheduleCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getCreatedBy()).orElseThrow(NullPointerException::new);
        Schedule schedule = new Schedule(requestDto, user);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        List<User> managers = requestDto.getManagers().stream().map(u->userRepository.findById(u).orElseThrow(NullPointerException::new)).toList();
        List<Manage> manageList = new ArrayList<>();
        for(User manager : managers) {
            Manage manage = new Manage(schedule,manager);
            manageList.add(manage);
        }
        List<Manage> manages = manageRepository.saveAll(manageList);
        ScheduleDetailResponseDto responseDto = new ScheduleDetailResponseDto(saveSchedule,manages);
        return responseDto;
    }

    public ScheduleDetailResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        ScheduleDetailResponseDto responseDto = new ScheduleDetailResponseDto(schedule);
        return responseDto;
    }

    public ScheduleDetailResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        Schedule saveSchedule = schedule.update(requestDto);
        scheduleRepository.save(saveSchedule);
        manageRepository.deleteAll(manageRepository.findAllByScheduleId(schedule.getId()));
        List<Manage> manageList = new ArrayList<>();
        List<User> managers = requestDto.getManagers().stream().map(u->userRepository.findById(u).orElseThrow(NullPointerException::new)).toList();
        for(User manager : managers) {
            Manage manage = new Manage(schedule,manager);
            manageList.add(manage);
        }
        List<Manage> saveManage = manageRepository.saveAll(manageList);
        return new ScheduleDetailResponseDto(saveSchedule, saveManage);
    }


    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Schedule> schedules = scheduleRepository.findALlByOrderByModifiedAtDesc(pageable);
        return schedules.map(schedule -> new ScheduleResponseDto(schedule));
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
