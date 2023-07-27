package com.coopeuch.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.coopeuch.challenge.domain.entities.TaskEntity;
import com.coopeuch.challenge.domain.models.CreateTaskRequest;
import com.coopeuch.challenge.domain.models.CreateTaskResponse;
import com.coopeuch.challenge.domain.models.DeleteTaskResponse;
import com.coopeuch.challenge.domain.models.GetTaskResponse;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.domain.models.UpdateTaskRequest;
import com.coopeuch.challenge.domain.models.UpdateTaskResponse;
import com.coopeuch.challenge.domain.services.TaskServiceImplementation;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TaskServiceTest {

  private TaskRepository taskRepository;
  private TaskServiceImplementation taskService;

  @BeforeEach
  void setUp() {
    // Preparar el repositorio mockado
    taskRepository = Mockito.mock(TaskRepository.class);
    taskService = new TaskServiceImplementation(taskRepository);
  }

  @Test
  void testCreate() {
    // Preparar los datos de entrada para el método create
    CreateTaskRequest createRequest = new CreateTaskRequest("Descripción", true);

    // Configurar el comportamiento esperado del repositorio mockado
    TaskEntity savedEntity = new TaskEntity(1, "Descripción", LocalDateTime.now(), true);
    Mockito.when(taskRepository.save(Mockito.any(TaskEntity.class))).thenReturn(savedEntity);

    // Ejecutar el método create
    TaskResponse<CreateTaskResponse> response = taskService.create(createRequest);

    // Verificar que el resultado sea el esperado
    Assertions.assertEquals("SUCCESS", response.getCode());
    Assertions.assertEquals("La tarea se ha ejecutado satisfactoriamente", response.getMessage());
    Assertions.assertNull(response.getErrors());
    Assertions.assertNotNull(response.getData());

    CreateTaskResponse responseData = response.getData();
    Assertions.assertEquals(1, responseData.getTaskId());
    Assertions.assertEquals("Descripción", responseData.getDescription());
    Assertions.assertTrue(responseData.isActive());

    // Verificar que el método save fue llamado en el repositorio con los parámetros
    // correctos
    Mockito.verify(taskRepository).save(Mockito.any(TaskEntity.class));
  }

  @Test
  void testUpdate() {
    // Preparar los datos de entrada para el método update
    UpdateTaskRequest updateRequest = new UpdateTaskRequest(1l, "Nueva descripción", false);

    // Configurar el comportamiento esperado del repositorio mockado
    TaskEntity updatedEntity = new TaskEntity(1, "Nueva descripción", LocalDateTime.now(), false);
    Mockito.when(taskRepository.update(Mockito.any(TaskEntity.class))).thenReturn(updatedEntity);

    // Ejecutar el método update
    TaskResponse<UpdateTaskResponse> response = taskService.update(updateRequest);

    // Verificar que el resultado sea el esperado
    Assertions.assertEquals("SUCCESS", response.getCode());
    Assertions.assertEquals("La tarea se ha actualizado satisfactoriamente", response.getMessage());
    Assertions.assertNull(response.getErrors());
    Assertions.assertNotNull(response.getData());

    UpdateTaskResponse responseData = response.getData();
    Assertions.assertEquals(1, responseData.getTaskId());
    Assertions.assertEquals("Nueva descripción", responseData.getDescription());
    Assertions.assertFalse(responseData.isActive());

    // Verificar que el método update fue llamado en el repositorio con los
    // parámetros correctos
    Mockito.verify(taskRepository).update(Mockito.any(TaskEntity.class));
  }

  @Test
  public void testDelete() {
    // Arrange
    long taskId = 1L;

    // Act
    TaskResponse<DeleteTaskResponse> response = taskService.delete(taskId);

    // Assert
    assertEquals("SUCCESS", response.getCode());
    assertEquals("La tarea se ha eliminado satisfactoriamente", response.getMessage());
    assertNull(response.getErrors());
    assertNull(response.getData());
    verify(taskRepository, times(1)).delete(taskId);
  }

  @Test
  public void testGetById() {
    // Arrange
    long taskId = 1L;
    TaskEntity entity = new TaskEntity(1L, "Task 1", LocalDateTime.now(), true);
    when(taskRepository.getById(taskId)).thenReturn(entity);

    // Act
    TaskResponse<GetTaskResponse> response = taskService.getById(taskId);

    // Assert
    assertEquals("SUCCESS", response.getCode());
    assertEquals("La tarea a ha obtenido satisfactoriamente", response.getMessage());
    assertNull(response.getErrors());
    assertEquals(entity.getTaskId(), response.getData().getTaskId());
    assertEquals(entity.getDescription(), response.getData().getDescription());
    assertEquals(entity.getCreateAt(), response.getData().getCreateAt());
    assertEquals(entity.isActive(), response.getData().isActive());
    verify(taskRepository, times(1)).getById(taskId);
  }

  @Test
  public void testGetAll() {
    // Arrange
    List<TaskEntity> entities = Arrays.asList(
        new TaskEntity(1L, "Task 1", LocalDateTime.now(), true),
        new TaskEntity(2L, "Task 2", LocalDateTime.now(), false));
    when(taskRepository.getAll()).thenReturn(entities);

    // Act
    TaskResponse<List<GetTaskResponse>> response = taskService.getAll();

    // Assert
    assertEquals("SUCCESS", response.getCode());
    assertEquals("Las tareas se han obtenido satisfactoriamente", response.getMessage());
    assertNull(response.getErrors());
    assertEquals(entities.size(), response.getData().size());

    for (int i = 0; i < entities.size(); i++) {
      TaskEntity entity = entities.get(i);
      GetTaskResponse getTaskResponse = response.getData().get(i);
      assertEquals(entity.getTaskId(), getTaskResponse.getTaskId());
      assertEquals(entity.getDescription(), getTaskResponse.getDescription());
      assertEquals(entity.getCreateAt(), getTaskResponse.getCreateAt());
      assertEquals(entity.isActive(), getTaskResponse.isActive());
    }

    verify(taskRepository, times(1)).getAll();
  }
}