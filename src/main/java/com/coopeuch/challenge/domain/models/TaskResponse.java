package com.coopeuch.challenge.domain.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TaskResponse extends RepresentationModel<TaskResponse> {
  private final long taskId;
  private final String description;
  private final LocalDateTime createAt;
  private final boolean active;
}
