package com.coopeuch.challenge.domain.services;

import java.util.List;

import com.coopeuch.challenge.domain.models.CreateTaskRequest;
import com.coopeuch.challenge.domain.models.CreateTaskResponse;
import com.coopeuch.challenge.domain.models.DeleteTaskResponse;
import com.coopeuch.challenge.domain.models.GetTaskResponse;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.domain.models.UpdateTaskRequest;
import com.coopeuch.challenge.domain.models.UpdateTaskResponse;

public interface TaskService {
  TaskResponse<CreateTaskResponse> create(CreateTaskRequest createRequest);
  TaskResponse<UpdateTaskResponse> update(UpdateTaskRequest updateRequest);
  TaskResponse<DeleteTaskResponse> delete(long taskId);
  TaskResponse<GetTaskResponse> getById(long taskId);
  TaskResponse<List<GetTaskResponse>> getAll();
}
