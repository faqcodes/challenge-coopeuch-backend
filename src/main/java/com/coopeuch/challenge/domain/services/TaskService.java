package com.coopeuch.challenge.domain.services;

import java.util.List;

import com.coopeuch.challenge.domain.models.CreateTaskRequest;
import com.coopeuch.challenge.domain.models.CreateTaskResponse;
import com.coopeuch.challenge.domain.models.GetTaskResponse;
import com.coopeuch.challenge.domain.models.UpdateTaskRequest;
import com.coopeuch.challenge.domain.models.UpdateTaskResponse;

public interface TaskService {
  CreateTaskResponse create(CreateTaskRequest createRequest);
  UpdateTaskResponse update(UpdateTaskRequest updateRequest);
  void delete(long taskId);
  GetTaskResponse getById(long taskId);
  List<GetTaskResponse> getAll();
}
