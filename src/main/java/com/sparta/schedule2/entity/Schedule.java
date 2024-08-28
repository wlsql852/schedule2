package com.sparta.schedule2.entity;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false, length=50)
    private String username;
    @Column(name="title", nullable=false, length=100)
    private String title;
    @Column(name="content", nullable=false, length=500)
    private String content;


    public Schedule(ScheduleCreateRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public Schedule update(ScheduleUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        return this;
    }
}
