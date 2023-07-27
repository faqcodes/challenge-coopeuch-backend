package com.coopeuch.challenge.persistences.repositories;

import java.util.List;

import com.coopeuch.challenge.domain.entities.TaskEntity;

public class TaskRepositoryImplementation implements TaskRepository {

  private TaskCrudRepository taskRepository;

  public TaskRepositoryImplementation(TaskCrudRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public TaskEntity save(TaskEntity task) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public TaskEntity update(TaskEntity task) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(long taskId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public TaskEntity getById(long taskId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public List<TaskEntity> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }
  
}
