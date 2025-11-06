package com.schedule.repository;

import com.schedule.entity.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();
    List<Schedule> findAllByAuthorOrderByModifiedAtDesc(String author);

    @Query("SELECT MAX(s.displayId) FROM Schedule s")
    Integer findMaxDisplayId();

    List<Schedule> findAll(Sort sort);

}
