package com.coopeuch.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.coopeuch.challenge.domain.entities.TaskEntity;
import com.coopeuch.challenge.domain.models.TaskRequest;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.domain.services.TaskServiceImplementation;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class TaskServiceTest {

  private TaskRepository taskRepository;
  private TaskServiceImplementation<TaskRepository> taskService;

  @BeforeEach
  void setUp() {
    // Preparar el repositorio mockado
    taskRepository = Mockito.mock(TaskRepository.class);
    taskService = new TaskServiceImplementation<>(taskRepository);
  }

  @Test
  void testCreate() {
    // Preparar los datos de entrada para el método create
    var createRequest = new TaskRequest("Descripción", true);

    // Configurar el comportamiento esperado del repositorio mockado
    TaskEntity savedEntity = new TaskEntity(1, "Descripción", LocalDateTime.now(), true);
    Mockito.when(taskRepository.save(Mockito.any(TaskEntity.class))).thenReturn(savedEntity);

    // Ejecutar el método create
    var response = taskService.create(createRequest);

    // Verificar que el resultado sea el esperado
    Assertions.assertEquals("SUCCESS", response.getCode());
    Assertions.assertEquals("La tarea se ha ejecutado satisfactoriamente", response.getMessage());
    Assertions.assertNull(response.getErrors());
    Assertions.assertNotNull(response.getData());

    var responseData = (TaskResponse) response.getData();
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
    var updateRequest = new TaskRequest(1l, "Nueva descripción", false);

    // Configurar el comportamiento esperado del repositorio mockado
    TaskEntity updatedEntity = new TaskEntity(1, "Nueva descripción", LocalDateTime.now(), false);
    Mockito.when(taskRepository.update(Mockito.any(TaskEntity.class))).thenReturn(updatedEntity);

    // Ejecutar el método update
    var response = taskService.update(updateRequest);

    // Verificar que el resultado sea el esperado
    Assertions.assertEquals("SUCCESS", response.getCode());
    Assertions.assertEquals("La tarea se ha actualizado satisfactoriamente", response.getMessage());
    Assertions.assertNull(response.getErrors());
    Assertions.assertNotNull(response.getData());

    var responseData = (TaskResponse) response.getData();
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
    var response = taskService.delete(taskId);

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
    var response = taskService.getById(taskId);

    // Assert
    assertEquals("SUCCESS", response.getCode());
    assertEquals("La tarea a ha obtenido satisfactoriamente", response.getMessage());
    assertNull(response.getErrors());

    var responseData = (TaskResponse) response.getData();
    assertEquals(entity.getTaskId(), responseData.getTaskId());
    assertEquals(entity.getDescription(), responseData.getDescription());
    assertEquals(entity.getCreateAt(), responseData.getCreateAt());
    assertEquals(entity.isActive(), responseData.isActive());

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
    var response = taskService.getAll();

    // Assert
    assertEquals("SUCCESS", response.getCode());
    assertEquals("Las tareas se han obtenido satisfactoriamente", response.getMessage());
    assertNull(response.getErrors());

    var responseData = (List<TaskResponse>) response.getData();
    assertEquals(entities.size(), responseData.size());

    for (int i = 0; i < entities.size(); i++) {
      TaskEntity entity = entities.get(i);
      var getTaskResponse = responseData.get(i);

      assertEquals(entity.getTaskId(), getTaskResponse.getTaskId());
      assertEquals(entity.getDescription(), getTaskResponse.getDescription());
      assertEquals(entity.getCreateAt(), getTaskResponse.getCreateAt());
      assertEquals(entity.isActive(), getTaskResponse.isActive());
    }

    verify(taskRepository, times(1)).getAll();
  }
}