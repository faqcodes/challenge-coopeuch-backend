package com.coopeuch.challenge.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coopeuch.challenge.domain.entities.TaskEntity;
import com.coopeuch.challenge.domain.models.ServiceResponse;
import com.coopeuch.challenge.domain.models.TaskRequest;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

@Service
public class TaskServiceImplementation<T> implements CrudService<TaskRequest, TaskResponse> {

  private TaskRepository taskRepository;

  public TaskServiceImplementation(T repository) {
    this.taskRepository = (TaskRepository) repository;
  }

  @Override
  public ServiceResponse<TaskResponse> create(TaskRequest createRequest) {
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
    var taskResponse = new TaskResponse(
        entity.getTaskId(),
        entity.getDescription(),
        entity.getCreateAt(),
        entity.isActive());

    // Se retorna el objeto creado
    return new ServiceResponse<>(
        "SUCCESS",
        "La tarea se ha ejecutado satisfactoriamente",
        null,
        taskResponse);
  }

  @Override
  public ServiceResponse<TaskResponse> update(TaskRequest updateRequest) {
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
      return new ServiceResponse<>(
          "ERROR",
          "Error al actualizar la tarea",
          null,
          null);
    }

    // Se crea el objeto a retornar con la nuevo info
    var taskResponse = new TaskResponse(
        entity.getTaskId(),
        entity.getDescription(),
        entity.getCreateAt(),
        entity.isActive());

    // Se retorna el objeto creado
    return new ServiceResponse<>(
        "SUCCESS",
        "La tarea se ha actualizado satisfactoriamente",
        null,
        taskResponse);
  }

  @Override
  public ServiceResponse<TaskResponse> delete(long taskId) {
    // Se elimina en la BD
    taskRepository.delete(taskId);

    // Se retorna el objeto creado
    return new ServiceResponse<>(
        "SUCCESS",
        "La tarea se ha eliminado satisfactoriamente",
        null,
        null);
  }

  @Override
  public ServiceResponse<TaskResponse> getById(long taskId) {
    // Se obtiene la tarea desde la BD
    var entity = taskRepository.getById(taskId);

    if (entity == null) {
      return new ServiceResponse<>(
          "SUCCESS",
          "No se encontró la tarea",
          null,
          null);
    }

    // Se crea el objeto a retornar con la nuevo info
    var taskResponse = new TaskResponse(
        entity.getTaskId(),
        entity.getDescription(),
        entity.getCreateAt(),
        entity.isActive());

    return new ServiceResponse<>(
        "SUCCESS",
        "La tarea a ha obtenido satisfactoriamente",
        null,
        taskResponse);
  }

  @Override
  public ServiceResponse<List<TaskResponse>> getAll() {
    // Se obtiene la tarea desde la BD
    var entity = taskRepository.getAll();

    if (entity.isEmpty()) {
      return new ServiceResponse<>(
          "SUCCESS",
          "No se encontraron tareas",
          null,
          null);
    }

    // Se crea el objeto a retornar con la nuevo info
    var getAllTaskResponse = entity
        .stream()
        .map(task -> new TaskResponse(
            task.getTaskId(),
            task.getDescription(),
            task.getCreateAt(),
            task.isActive()))
        .toList();

    return new ServiceResponse<>(
        "SUCCESS",
        "Las tareas se han obtenido satisfactoriamente",
        null,
        getAllTaskResponse);
  }

}
