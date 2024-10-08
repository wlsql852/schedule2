package com.sparta.schedule2.repository;

import com.sparta.schedule2.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findById(long id);
    Page<Schedule> findALlByOrderByModifiedAtDesc(Pageable pageable);
}
