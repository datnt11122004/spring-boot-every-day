package com.example.demo.repositories.abstracts.user;

import com.example.demo.models.User;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.repositories.abstracts.BaseRepositoryAbstract;
import com.example.demo.repositories.interfaces.user.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public abstract class UserRepositoryAbstract extends BaseRepository<User, UUID>{
    public UserRepositoryAbstract(Class<User> entityClass) {
        super(entityClass);
    }
}