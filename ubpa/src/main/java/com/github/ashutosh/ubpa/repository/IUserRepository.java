package com.github.ashutosh.ubpa.repository;

import com.github.ashutosh.ubpa.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User,Long> {

    public User findByEmail(String email);
}
