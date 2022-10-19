package com.example.fpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fpl.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	public Schedule findByScheduleKey(String scheduleKey);
}
