package com.coopeuch.challenge.domain.services;

import org.springframework.stereotype.Service;

import com.coopeuch.challenge.domain.models.TaskRequest;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

@Service
public class CreateTaskServiceImplementation implements CreateTaskService {

  private TaskRepository taskRepository;

  public CreateTaskServiceImplementation(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public CrudService<TaskRequest, TaskResponse> create() {
    return new TaskServiceImplementation<>(taskRepository);
  }
  
}
