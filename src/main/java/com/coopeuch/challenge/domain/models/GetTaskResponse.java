package com.coopeuch.challenge.domain.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTaskResponse extends RepresentationModel<TaskResponse<GetTaskResponse>>{
  private final long taskId;
  private final String description;
  private final LocalDateTime createAt;
  private final boolean active;
}
