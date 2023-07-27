package com.coopeuch.challenge.domain.services;

import org.springframework.stereotype.Service;

import com.coopeuch.challenge.persistences.repositories.TaskRepository;

@Service
public class CreateTaskServiceImplementation implements CreateTaskService {

  private TaskRepository taskRepository;

  @Override
  public TaskService create() {
    return new TaskServiceImplementation(taskRepository);
  }
  
}
