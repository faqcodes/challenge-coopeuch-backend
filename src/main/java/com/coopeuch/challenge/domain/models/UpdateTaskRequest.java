package com.coopeuch.challenge.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTaskRequest {
  @Min(value = 1, message = "El campo taskId es requerido")
  @NotNull(message = "El campo taskId no puede ser nulo")
  private long taskId;

  @NotBlank(message = "El campo description es requerido")
  @NotEmpty(message = "El campo description es requerido")
  private String description;

  @NotNull(message = "El campo active es requerido")
  private boolean active;
}
