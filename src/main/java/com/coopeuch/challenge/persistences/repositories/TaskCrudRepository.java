package com.coopeuch.challenge.persistences.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coopeuch.challenge.persistences.entities.TaskDataEntity;

@Repository
public interface TaskCrudRepository extends CrudRepository<TaskDataEntity, Long> {}
