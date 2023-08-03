package com.coopeuch.challenge.domain.models;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ServiceResponse<T> extends RepresentationModel<ServiceResponse<T>> {
  private final String code;
  private final String message;
  private final List<String> errors;
  private final T data;
}
