package com.coopeuch.challenge.persistences.repositories;

import java.util.Collections;
import java.util.List;

import com.coopeuch.challenge.domain.entities.TaskEntity;
import com.coopeuch.challenge.persistences.entities.TaskDataEntity;

public class TaskRepositoryImplementation implements TaskRepository {

  private TaskCrudRepository taskRepository;

  public TaskRepositoryImplementation(TaskCrudRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public TaskEntity save(TaskEntity task) {
    var dataEntity = new TaskDataEntity(
      task.getTaskId(),
      task.getDescription(),
      task.getCreateAt(),
      task.isActive()
    );

    var data = taskRepository.save(dataEntity);
    
    return new TaskEntity(
      data.getTaskId(), 
      data.getDescription(),
      data.getCreateAt(),
      data.getActive()
    );
  }

  @Override
  public TaskEntity update(TaskEntity task) {

    var data = taskRepository.findById(task.getTaskId());

    if (!data.isPresent()) {
      return null;
    }

    var dataEntity = new TaskDataEntity(
      data.get().getTaskId(),
      task.getDescription(),
      data.get().getCreateAt(),
      task.isActive()
    );

    var dataUpdated = taskRepository.save(dataEntity);
    
    return new TaskEntity(
      dataUpdated.getTaskId(), 
      dataUpdated.getDescription(),
      dataUpdated.getCreateAt(),
      dataUpdated.getActive()
    );
  }

  @Override
  public void delete(long taskId) {
    taskRepository.deleteById(taskId);
  }

  @Override
  public TaskEntity getById(long taskId) {
    var data = taskRepository.findById(taskId);

    if (!data.isPresent()) {
      return null;
    }

    return new TaskEntity(
      data.get().getTaskId(), 
      data.get().getDescription(),
      data.get().getCreateAt(),
      data.get().getActive()
    );
  }

  @Override
  public List<TaskEntity> getAll() {
    var data = (List<TaskDataEntity>) taskRepository.findAll();

    if (data.isEmpty()) {
      return Collections.emptyList();
    }

    return data
            .stream()
            .map(d -> new TaskEntity(
              d.getTaskId(), 
              d.getDescription(), 
              d.getCreateAt(), 
              d.getActive()
            ))
            .toList();
  }
  
}
