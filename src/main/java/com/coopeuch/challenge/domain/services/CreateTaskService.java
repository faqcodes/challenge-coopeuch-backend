package com.coopeuch.challenge.domain.services;

import com.coopeuch.challenge.domain.models.TaskRequest;
import com.coopeuch.challenge.domain.models.TaskResponse;

public interface CreateTaskService {
  CrudService<TaskRequest, TaskResponse> create();
}
