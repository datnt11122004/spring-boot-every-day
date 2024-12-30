package com.example.demo.repositories;

import com.example.demo.repositories.abstracts.BaseRepositoryAbstract;
import com.example.demo.repositories.interfaces.BaseRepositoryInterface;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class BaseRepository<BaseModel extends com.example.demo.models.BaseModel, UUID> extends BaseRepositoryAbstract<BaseModel, UUID> implements BaseRepositoryInterface<BaseModel, UUID> {
    public BaseRepository(Class<BaseModel> entityClass) {
        super(entityClass);
    }

    @Override
    public BaseModel save(BaseModel entity) {
        return this.saveEntity(entity);
    }

    @Override
    public BaseModel update(BaseModel entity) {
        return this.updateEntity(entity);
    }

    @Override
    public Optional<BaseModel> findById(UUID uuid) {
        return this.findByIdEntity(uuid);
    }

    @Override
    public List<BaseModel> findAll() {
        return this.findAllEntity();
    }

    @Override
    public Optional<BaseModel> delete(UUID id) {
        return this.deleteEntity(id);
    }

    @Override
    public Optional<BaseModel> softDelete(UUID id) {
        return this.softDeleteEntity(id);
    }
}
