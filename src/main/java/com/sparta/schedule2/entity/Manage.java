package com.sparta.schedule2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="manage")
public class Manage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //담당자아이디(지연로딩)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id", nullable = false)
    private User manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id", nullable = false)
    private Schedule schedule;

    public Manage(Schedule schedule, User manager) {
        this.schedule = schedule;
        this.manager = manager;
    }
}
