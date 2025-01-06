package com.example.demo.services.abstracts;

import com.example.demo.models.BaseModel;
import com.example.demo.repositories.BaseRepository;

public abstract class BaseServiceAbstract<T extends BaseModel, ID> {

    protected BaseRepository<T, ID> repository;

    public BaseServiceAbstract(
            BaseRepository<T, ID> repository
    ) {
        this.repository = repository;
    }

    public T saveRepository(T entity) {
        return repository.save(entity);
    }

    public T updateRepository(T entity) {
        return repository.update(entity);
    }

    public T findByIdRepository(ID id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<T> findAllRepository() {
        return repository.findAll();
    }

    public T deleteRepository(ID id) {
        return repository.delete(id).orElse(null);
    }

    public T softDeleteRepository(ID id) {
        return repository.softDelete(id).orElse(null);
    }
}