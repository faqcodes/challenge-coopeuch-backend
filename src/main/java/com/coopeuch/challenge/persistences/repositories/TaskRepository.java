package com.coopeuch.challenge.persistences.repositories;

import java.util.List;

import com.coopeuch.challenge.domain.entities.TaskEntity;

public interface TaskRepository {
  TaskEntity save(TaskEntity task);
  TaskEntity update(TaskEntity task);
  void delete(long taskId);
  TaskEntity getById(long taskId);
  List<TaskEntity> getAll();
}
