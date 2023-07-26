package com.coopeuch.challenge.domain.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskEntity {
  private final long taskId;
  private final String description;
  private final Date createAt;
  private final boolean active;
}
