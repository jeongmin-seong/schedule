package com.schedule.repository;

import com.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();
    List<Schedule> findAllByAuthorOrderByModifiedAtDesc(String authorName);

    @Modifying
    @Query(value = "ALTER TABLE schedule AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    @Modifying
    @Transactional
    default void deleteAllAndReset() {
        deleteAll();
        resetAutoIncrement();
    }
}
