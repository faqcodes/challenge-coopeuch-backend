package com.coopeuch.challenge.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class TaskRequest {
  private long taskId;

  @NotBlank(message = "El campo description es requerido")
  @NotEmpty(message = "El campo description es requerido")
  private String description;

  @NotNull(message = "El campo active es requerido")
  private boolean active;

  public TaskRequest(String description, boolean active) {
    this.description = description;
    this.active = active;
  }

  public TaskRequest(long taskId, String description, boolean active) {
    this.taskId = taskId;
    this.description = description;
    this.active = active;
  }
}
