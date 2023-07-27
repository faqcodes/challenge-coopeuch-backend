package com.coopeuch.challenge.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.coopeuch.challenge.domain.services.TaskService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/api/tasks/v1")
public class TaskController extends BaseController {

  // private CreateTaskService createTaskService;

  // public TaskController(CreateTaskService createTaskService) {
  // this.createTaskService = createTaskService;
  // }

  private TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping()
  public ResponseEntity<TaskResponse<CreateTaskResponse>> create(@Valid @RequestBody CreateTaskRequest createRequest) {
    // var taskService = createTaskService.create();

    var response = taskService.create(createRequest);

    // 201
    return ResponseEntity
        .created(URI.create(""))
        .body(response);
  }

  @PatchMapping
  public ResponseEntity<TaskResponse<UpdateTaskResponse>> update(@Valid @RequestBody UpdateTaskRequest updateRequest) {
    // var taskService = createTaskService.create();

    var response = taskService.update(updateRequest);

    // 200
    return ResponseEntity
        .ok()
        .body(response);
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity<TaskResponse<DeleteTaskResponse>> delete(
      @PathVariable @Min(value = 1, message = "El campo taskId es requerido") long taskId) {
    // var taskService = createTaskService.create();

    taskService.delete(taskId);

    return ResponseEntity
        .noContent()
        .build();
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskResponse<GetTaskResponse>> getById(
      @PathVariable @Min(value = 1, message = "El campo taskId es requerido") long taskId) {
    // var taskService = createTaskService.create();

    var response = taskService.getById(taskId);

    return ResponseEntity
        .ok()
        .body(response);
  }

  @GetMapping
  public ResponseEntity<TaskResponse<List<GetTaskResponse>>> getAll() {
    // var taskService = createTaskService.create();

    var response = taskService.getAll();

    return ResponseEntity
        .ok()
        .body(response);
  }
}
