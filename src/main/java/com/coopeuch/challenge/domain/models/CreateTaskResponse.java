package com.coopeuch.challenge.domain.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTaskResponse {
  private final long taskId;
  private final String description;
  private final LocalDateTime createAt;
  private final boolean active;
}
