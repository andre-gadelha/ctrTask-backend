package com.ctrTask.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoty extends JpaRepository<Task, Integer> {
}
