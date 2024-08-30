package com.sparta.schedule2.entity;

import com.sparta.schedule2.dto.schedule.request.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.schedule.request.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, length=100)
    private String title;
    @Column(name="content", nullable=false, length=500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable=false)
    private User createdBy;

    @OneToMany(mappedBy = "schedule", orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", orphanRemoval = true)
    private List<Manage> manageList = new ArrayList<>();


    public Schedule(ScheduleCreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public Schedule(ScheduleCreateRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.createdBy = user;
    }


    public Schedule update(ScheduleUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        return this;
    }
}
