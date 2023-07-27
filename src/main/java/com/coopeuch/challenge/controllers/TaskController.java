package com.coopeuch.challenge.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coopeuch.challenge.domain.models.CreateTaskRequest;
import com.coopeuch.challenge.domain.models.CreateTaskResponse;
import com.coopeuch.challenge.domain.models.DeleteTaskResponse;
import com.coopeuch.challenge.domain.models.GetTaskResponse;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.domain.models.UpdateTaskRequest;
import com.coopeuch.challenge.domain.models.UpdateTaskResponse;
import com.coopeuch.challenge.domain.services.CreateTaskService;

@RestController
@RequestMapping("/api/tasks/v1")
public class TaskController {
  
  private CreateTaskService createTaskService;

  public TaskController(CreateTaskService createTaskService) {
    this.createTaskService = createTaskService;
  }

  @PostMapping()
  public ResponseEntity<TaskResponse<CreateTaskResponse>> create(@RequestBody CreateTaskRequest createRequest) {
    var taskService = createTaskService.create();

    var response = taskService.create(createRequest);

    return ResponseEntity
        .created(URI.create(""))
        .body(response);
  }

  @PatchMapping
  public ResponseEntity<TaskResponse<UpdateTaskResponse>> update(@RequestBody UpdateTaskRequest updateRequest) {
    var taskService = createTaskService.create();

    var response = taskService.update(updateRequest);

    return ResponseEntity
        .ok()
        .body(response);
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity<TaskResponse<DeleteTaskResponse>> delete(@Param(value = "taskId") long taskId) {
    var taskService = createTaskService.create();

    taskService.delete(taskId);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskResponse<GetTaskResponse>> getById(@Param(value = "taskId") long taskId) {
    var taskService = createTaskService.create();

    var response = taskService.getById(taskId);

    return ResponseEntity
        .ok()
        .body(response);
  }

  @GetMapping
  public ResponseEntity<TaskResponse<List<GetTaskResponse>>> getAll() {
    var taskService = createTaskService.create();

    var response = taskService.getAll();

    return ResponseEntity
        .ok()
        .body(response);
  }
}
