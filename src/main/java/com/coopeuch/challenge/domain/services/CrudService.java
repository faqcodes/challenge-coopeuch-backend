package com.coopeuch.challenge.domain.services;

import java.util.List;

import com.coopeuch.challenge.domain.models.ServiceResponse;

public interface CrudService<T, U> {
  ServiceResponse<U> create(T createRequest);
  ServiceResponse<U> update(T updateRequest);
  ServiceResponse<U> delete(long id);
  ServiceResponse<U> getById(long id);
  ServiceResponse<List<U>> getAll();
}
