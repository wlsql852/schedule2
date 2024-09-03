package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.manage.request.ManageCreateRequestDto;
import com.sparta.schedule2.dto.manage.request.ManageUpdateRequestDto;
import com.sparta.schedule2.dto.schedule.response.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.user.response.UserSimpleResponseDto;
import com.sparta.schedule2.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//담당자 배정 관련 페이지
@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ManageController {
    private final ManageService manageService;

    /**
     * 담당 매니저 배정하기
     * @Param  등록할 일정 아이디, 작성자 아이디, 배정할 담당자 아이디가 들어있는 정보 request
     * @return 등록된 후의 일정 정보
     * */
    @PostMapping("/manage")
    public ScheduleDetailResponseDto createManage(@RequestBody ManageCreateRequestDto requestDto) {
        return manageService.createManage(requestDto);
    }

    /**
     * 해당 일정의 담당 매니저 리스트 조회
     * @Param  등록할 일정 아이디, 생성자 아이디, 배정할 담당자 아이디가 들어있는 정보 request
     * @return 등록된 후의 일정 정보
     * */
    @GetMapping("schedule/{scheduleId}")
    public List<UserSimpleResponseDto> getManagers(@PathVariable Long scheduleId) {
        return manageService.getManagers(scheduleId);
    }

    /**
     * 해당 일정의 담당자 수정
     * @Param  수정할 일정 아이디, 일정의 생성자 아이디, 수정될 배정할 담당자 아이디가 들어있는 정보 request
     * @return 수정된 후의 일정 정보
     * */
    @PutMapping("schedule/{scheduleId}")
    public ScheduleDetailResponseDto updateManage (@PathVariable Long scheduleId, @RequestBody ManageUpdateRequestDto requestDto) {
        return manageService.updateManage(scheduleId, requestDto);
    }

    /**
     * 해당 일정의 담당자 삭제
     * @Param  수정할 일정 아이디, 일정의 생성자 아이디, 수정될 배정할 담당자 아이디가 들어있는 정보 request
     * @return 수정된 후의 일정 정보
     * */
    @DeleteMapping("/{scheduleId}/{managerId}")
    public void deleteManage(@PathVariable Long scheduleId, @PathVariable Long managerId) {
        manageService.deleteManage(scheduleId, managerId);
    }
}
