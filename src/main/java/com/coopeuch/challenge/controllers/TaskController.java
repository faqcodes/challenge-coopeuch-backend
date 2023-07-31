package com.coopeuch.challenge.controllers;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
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
    var createdUri = URI.create("");
    var response = taskService.create(createRequest);

    if (response != null) {
      var taskId = response.getData().getTaskId();
      var link = linkTo(methodOn(TaskController.class).getById(taskId)).withSelfRel();

      response.add(link);
      createdUri = link.toUri();
    }

    // 201
    return ResponseEntity
        .created(createdUri)
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

    // 204
    return ResponseEntity
        .noContent()
        .build();
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<TaskResponse<GetTaskResponse>> getById(
      @PathVariable @Min(value = 1, message = "El campo taskId es requerido") long taskId) {
    // var taskService = createTaskService.create();

    var response = taskService.getById(taskId);

    if (response != null) {
      response.add((linkTo(methodOn(TaskController.class).getById(taskId))).withSelfRel());
    }

    // 200
    return ResponseEntity
        .ok()
        .body(response);
  }

  @GetMapping
  public HttpEntity<TaskResponse<List<GetTaskResponse>>> getAll() {
    // var taskService = createTaskService.create();

    var response = taskService.getAll();

    if (response != null) {
      response.getData().forEach(data -> {
        data.add((linkTo(methodOn(TaskController.class).getById(data.getTaskId()))).withSelfRel());
        data.add((linkTo(methodOn(TaskController.class).delete(data.getTaskId()))).withRel("delete"));
      });

      response.add((linkTo(methodOn(TaskController.class).getAll())).withSelfRel());
    }

    // 200
    return ResponseEntity
        .ok()
        .body(response);
  }
}
