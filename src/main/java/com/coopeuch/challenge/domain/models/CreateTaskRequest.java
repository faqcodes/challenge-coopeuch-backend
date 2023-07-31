package com.coopeuch.challenge.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {
  @NotBlank(message = "El campo description es requerido")
  @NotEmpty(message = "El campo description es requerido")
  private String description;

  @NotNull(message = "El campo active es requerido")
  private boolean active;
}
