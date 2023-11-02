package com.TechConative.Test.utils;

import com.TechConative.Test.entity.Task;
import com.TechConative.Test.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataLoader {

    @Autowired
    private TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        Task task1 = new Task();
        task1.setTitle("Sample Task 1");
        task1.setDescription("This is a sample task.");
        task1.setCompleted(false);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Sample Task 2");
        task2.setDescription("Another sample task.");
        task2.setCompleted(true);
        taskRepository.save(task2);
    }
}