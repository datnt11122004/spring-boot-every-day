package com.example.demo.repositories.abstracts;

import com.example.demo.models.BaseModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public abstract class BaseRepositoryAbstract<T extends BaseModel, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    protected BaseRepositoryAbstract(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T saveEntity(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T updateEntity(T entity) {
        return entityManager.merge(entity);
    }

    public Optional<T> findByIdEntity(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id))
                .filter(entity -> !entity.isDeleted());
    }

    public List<T> findAllEntity() {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.deleted = false";
        return entityManager.createQuery(query, entityClass).getResultList();
    }

    public Optional<T> deleteEntity(ID id) {
        Optional<T> entity = findByIdEntity(id);
        entity.ifPresent(entityManager::remove);
        return entity;
    }

    public Optional<T> softDeleteEntity(ID id) {
        Optional<T> entity = findByIdEntity(id);
        entity.ifPresent(e -> {
            e.softDelete();
            entityManager.merge(e);
        });
        return entity;
    }
}