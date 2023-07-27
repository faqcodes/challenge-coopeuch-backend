package com.coopeuch.challenge;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.coopeuch.challenge.domain.entities.TaskEntity;
import com.coopeuch.challenge.persistences.entities.TaskDataEntity;
import com.coopeuch.challenge.persistences.repositories.TaskCrudRepository;
import com.coopeuch.challenge.persistences.repositories.TaskRepositoryImplementation;

public class TaskRepositoryTest {

  // Mock del repositorio Crud de tareas
  @Mock
  private TaskCrudRepository taskCrudRepository;

  // Clase bajo prueba
  private TaskRepositoryImplementation taskRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    taskRepository = new TaskRepositoryImplementation(taskCrudRepository);
  }

  @Test
  public void testSave() {
    // Datos de prueba
    TaskEntity task = new TaskEntity(1L, "Tarea 1", LocalDateTime.now(), true);
    TaskDataEntity dataEntity = new TaskDataEntity(1L, "Tarea 1", LocalDateTime.now(), true);

    // Comportamiento esperado del repositorio mock
    when(taskCrudRepository.save(any(TaskDataEntity.class))).thenReturn(dataEntity);

    // Ejecutar el método bajo prueba
    TaskEntity result = taskRepository.save(task);

    // Verificar que se haya guardado correctamente
    assertNotNull(result);
    assertEquals(task.getTaskId(), result.getTaskId());
    assertEquals(task.getDescription(), result.getDescription());
    // assertEquals(task.getCreateAt(), result.getCreateAt());
    assertEquals(task.isActive(), result.isActive());

    // Verificar que el método save del repositorio se haya llamado con los datos
    // correctos
    verify(taskCrudRepository).save(argThat(data -> data.getTaskId() == task.getTaskId() &&
        data.getDescription().equals(task.getDescription()) &&
        data.getCreateAt().equals(task.getCreateAt()) &&
        data.getActive() == task.isActive()));
  }

  @Test
  public void testUpdate_existingTask() {
    TaskEntity task = new TaskEntity(1L, "Tarea 1", LocalDateTime.now(), true);
    TaskDataEntity existingDataEntity = new TaskDataEntity(1L, "Tarea antigua", LocalDateTime.now(), false);
    TaskDataEntity updatedDataEntity = new TaskDataEntity(1L, "Tarea 1", LocalDateTime.now(), true);

    when(taskCrudRepository.findById(task.getTaskId())).thenReturn(Optional.of(existingDataEntity));
    when(taskCrudRepository.save(any(TaskDataEntity.class))).thenReturn(updatedDataEntity);

    TaskEntity result = taskRepository.update(task);

    assertNotNull(result);
    assertEquals(task.getTaskId(), result.getTaskId());
    assertEquals(task.getDescription(), result.getDescription());
    // assertEquals(task.getCreateAt(), result.getCreateAt());
    assertEquals(task.isActive(), result.isActive());

    verify(taskCrudRepository).findById(task.getTaskId());
    verify(taskCrudRepository).save(argThat(data -> data.getTaskId() == task.getTaskId() &&
        data.getDescription().equals(task.getDescription()) &&
        data.getCreateAt().equals(existingDataEntity.getCreateAt()) &&
        data.getActive() == task.isActive()));
  }

  @Test
  public void testUpdate_nonExistingTask() {
    TaskEntity task = new TaskEntity(1L, "Tarea 1", LocalDateTime.now(), true);

    when(taskCrudRepository.findById(task.getTaskId())).thenReturn(Optional.empty());

    TaskEntity result = taskRepository.update(task);

    assertNull(result);
    verify(taskCrudRepository).findById(task.getTaskId());
    verify(taskCrudRepository, never()).save(any(TaskDataEntity.class));
  }

  @Test
  public void testDelete() {
    long taskId = 1L;

    taskRepository.delete(taskId);

    verify(taskCrudRepository).deleteById(taskId);
  }

  @Test
  public void testGetById_existingTask() {
    long taskId = 1L;
    TaskDataEntity dataEntity = new TaskDataEntity(taskId, "Tarea 1", LocalDateTime.now(), true);

    when(taskCrudRepository.findById(taskId)).thenReturn(Optional.of(dataEntity));

    TaskEntity result = taskRepository.getById(taskId);

    assertNotNull(result);
    assertEquals(dataEntity.getTaskId(), result.getTaskId());
    assertEquals(dataEntity.getDescription(), result.getDescription());
    assertEquals(dataEntity.getCreateAt(), result.getCreateAt());
    assertEquals(dataEntity.getActive(), result.isActive());

    verify(taskCrudRepository).findById(taskId);
  }

  @Test
  public void testGetById_nonExistingTask() {
    long taskId = 1L;

    when(taskCrudRepository.findById(taskId)).thenReturn(Optional.empty());

    TaskEntity result = taskRepository.getById(taskId);

    assertNull(result);
    verify(taskCrudRepository).findById(taskId);
  }

  @Test
  public void testGetAll_noTasks() {
    when(taskCrudRepository.findAll()).thenReturn(Collections.emptyList());

    List<TaskEntity> result = taskRepository.getAll();

    assertNotNull(result);
    assertTrue(result.isEmpty());

    verify(taskCrudRepository).findAll();
  }

  @Test
  public void testGetAll_multipleTasks() {
    TaskDataEntity dataEntity1 = new TaskDataEntity(1L, "Tarea 1", LocalDateTime.now(), true);
    TaskDataEntity dataEntity2 = new TaskDataEntity(2L, "Tarea 2", LocalDateTime.now(), false);
    List<TaskDataEntity> dataEntities = Arrays.asList(dataEntity1, dataEntity2);

    when(taskCrudRepository.findAll()).thenReturn(dataEntities);

    List<TaskEntity> result = taskRepository.getAll();

    assertNotNull(result);
    assertEquals(dataEntities.size(), result.size());

    TaskEntity result1 = result.get(0);
    assertEquals(dataEntity1.getTaskId(), result1.getTaskId());
    assertEquals(dataEntity1.getDescription(), result1.getDescription());
    assertEquals(dataEntity1.getCreateAt(), result1.getCreateAt());
    assertEquals(dataEntity1.getActive(), result1.isActive());

    TaskEntity result2 = result.get(1);
    assertEquals(dataEntity2.getTaskId(), result2.getTaskId());
    assertEquals(dataEntity2.getDescription(), result2.getDescription());
    assertEquals(dataEntity2.getCreateAt(), result2.getCreateAt());
    assertEquals(dataEntity2.getActive(), result2.isActive());

    verify(taskCrudRepository).findAll();
  }

}