package com.sparta.schedule2.repository;

import com.sparta.schedule2.entity.Manage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManageRepository extends JpaRepository<Manage, Long> {
    List<Manage> findAllByScheduleId(Long id);
    Optional<Manage> findByScheduleIdAndManagerId(Long scheduleId, Long managerId);
}
