package com.example.demo.repositories.user;

import com.example.demo.models.User;
import com.example.demo.repositories.abstracts.user.UserRepositoryAbstract;
import com.example.demo.repositories.interfaces.user.UserRepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends UserRepositoryAbstract implements UserRepositoryInterface {
    public UserRepository() {
        super(User.class);
    }
}
