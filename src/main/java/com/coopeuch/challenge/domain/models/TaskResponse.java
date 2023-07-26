package com.coopeuch.challenge.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponse<T> {
  private final String code;
  private final String message;
  private final List<String> errors;
  private final T data;
}
