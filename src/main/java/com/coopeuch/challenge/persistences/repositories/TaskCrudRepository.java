package com.coopeuch.challenge.persistences.repositories;

import org.springframework.data.repository.CrudRepository;

import com.coopeuch.challenge.persistences.entities.TaskDataEntity;

public interface TaskCrudRepository extends CrudRepository<TaskDataEntity, Long> {}
