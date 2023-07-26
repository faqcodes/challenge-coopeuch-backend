package com.coopeuch.challenge.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTaskRequest {
  private final long taskId;
  private final String description;
  private final boolean active;
}
