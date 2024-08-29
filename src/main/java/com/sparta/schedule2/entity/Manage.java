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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    public Manage(Schedule schedule, User manager) {
        this.schedule = schedule;
        this.manager = manager;
    }
}
