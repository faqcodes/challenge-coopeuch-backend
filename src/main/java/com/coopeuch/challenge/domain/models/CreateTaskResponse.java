package com.coopeuch.challenge.domain.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskResponse {
  private long taskId;
  private String description;
  private LocalDateTime createAt;
  private boolean active;
}
