package com.coopeuch.challenge.persistences.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskDataEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taskId;

  private String description;

  private LocalDateTime createAt;

  private Boolean active;

  public TaskDataEntity() {
    super();
  }
}