package com.example.demo.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface BaseRepositoryInterface<T, ID> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    Optional<T> delete(ID id);

    Optional<T> softDelete(ID id);
}
