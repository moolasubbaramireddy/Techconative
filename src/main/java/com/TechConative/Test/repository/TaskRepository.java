package com.TechConative.Test.repository;

import com.TechConative.Test.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

