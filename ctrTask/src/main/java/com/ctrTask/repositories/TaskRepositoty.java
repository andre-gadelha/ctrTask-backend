package com.ctrTask.repositories;

import com.ctrTask.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoty extends JpaRepository<Task, Integer> {
}
