package com.coopeuch.challenge.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coopeuch.challenge.domain.entities.TaskEntity;
import com.coopeuch.challenge.domain.models.CreateTaskRequest;
import com.coopeuch.challenge.domain.models.CreateTaskResponse;
import com.coopeuch.challenge.domain.models.DeleteTaskResponse;
import com.coopeuch.challenge.domain.models.GetTaskResponse;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.domain.models.UpdateTaskRequest;
import com.coopeuch.challenge.domain.models.UpdateTaskResponse;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

@Service
public class TaskServiceImplementation implements TaskService {

  private TaskRepository taskRepository;

  public TaskServiceImplementation(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public TaskResponse<CreateTaskResponse> create(CreateTaskRequest createRequest) {
    // taskId se genera en la BD
    var taskId = 0l;

    // Se genera la fecha-hora de creación
    var createAt = LocalDateTime.now();

    // Se crea la entidad de negocio
    var taskEntity = new TaskEntity(
        taskId,
        createRequest.getDescription(),
        createAt,
        createRequest.isActive());

    // Se guarda en la BD
    var entity = taskRepository.save(taskEntity);

    // Se crea el objeto a retornar con la nuevo info
    var createTaskResponse = new CreateTaskResponse(
        entity.getTaskId(),
        entity.getDescription(),
        entity.getCreateAt(),
        entity.isActive());

    // Se retorna el objeto creado
    return new TaskResponse<>(
        "SUCCESS",
        "La tarea se ha ejecutado satisfactoriamente",
        null,
        createTaskResponse);
  }

  @Override
  public TaskResponse<UpdateTaskResponse> update(UpdateTaskRequest updateRequest) {
    // Se crea la entidad de negocio
    // (createAt no es necesario ya que no se modifica)
    var taskEntity = new TaskEntity(
        updateRequest.getTaskId(),
        updateRequest.getDescription(),
        null,
        updateRequest.isActive());

    // Se actualiza en la BD
    var entity = taskRepository.update(taskEntity);

    if (entity == null) {
      // Se retorna el objeto con error
      return new TaskResponse<>(
          "ERROR",
          "Error al actualizar la tarea",
          null,
          null);
    }

    // Se crea el objeto a retornar con la nuevo info
    var updateTaskResponse = new UpdateTaskResponse(
        entity.getTaskId(),
        entity.getDescription(),
        entity.getCreateAt(),
        entity.isActive());

    // Se retorna el objeto creado
    return new TaskResponse<>(
        "SUCCESS",
        "La tarea se ha actualizado satisfactoriamente",
        null,
        updateTaskResponse);
  }

  @Override
  public TaskResponse<DeleteTaskResponse> delete(long taskId) {
    // Se elimina en la BD
    taskRepository.delete(taskId);

    // Se retorna el objeto creado
    return new TaskResponse<>(
        "SUCCESS",
        "La tarea se ha eliminado satisfactoriamente",
        null,
        null);
  }

  @Override
  public TaskResponse<GetTaskResponse> getById(long taskId) {
    // Se obtiene la tarea desde la BD
    var entity = taskRepository.getById(taskId);

    if (entity == null) {
      return new TaskResponse<>(
          "ERROR",
          "No se encontró la tarea",
          null,
          null);
    }

    // Se crea el objeto a retornar con la nuevo info
    var getTaskResponse = new GetTaskResponse(
        entity.getTaskId(),
        entity.getDescription(),
        entity.getCreateAt(),
        entity.isActive());

    return new TaskResponse<>(
        "SUCCESS",
        "La tarea a ha obtenido satisfactiamente",
        null,
        getTaskResponse);
  }

  @Override
  public TaskResponse<List<GetTaskResponse>> getAll() {
    // Se obtiene la tarea desde la BD
    var entity = taskRepository.getAll();

    if (entity.isEmpty()) {
      return new TaskResponse<>(
          "ERROR",
          "No se encontraron tareas",
          null,
          null);
    }

    // Se crea el objeto a retornar con la nuevo info
    var getAllTaskResponse = entity
        .stream()
        .map(task -> new GetTaskResponse(
            task.getTaskId(),
            task.getDescription(),
            task.getCreateAt(),
            task.isActive()))
        .toList();

    return new TaskResponse<>(
        "SUCCESS",
        "Las tareas se han obtenido satisfactiamente",
        null,
        getAllTaskResponse);
  }

}
