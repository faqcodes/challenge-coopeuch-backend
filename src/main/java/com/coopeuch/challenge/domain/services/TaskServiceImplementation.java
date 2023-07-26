package com.coopeuch.challenge.domain.services;

import java.util.List;

import com.coopeuch.challenge.domain.models.CreateTaskRequest;
import com.coopeuch.challenge.domain.models.CreateTaskResponse;
import com.coopeuch.challenge.domain.models.GetTaskResponse;
import com.coopeuch.challenge.domain.models.UpdateTaskRequest;
import com.coopeuch.challenge.domain.models.UpdateTaskResponse;

public class TaskServiceImplementation implements TaskService {

  @Override
  public CreateTaskResponse create(CreateTaskRequest createRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public UpdateTaskResponse update(UpdateTaskRequest updateRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(long taskId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public GetTaskResponse getById(long taskId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public List<GetTaskResponse> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }
  
}
