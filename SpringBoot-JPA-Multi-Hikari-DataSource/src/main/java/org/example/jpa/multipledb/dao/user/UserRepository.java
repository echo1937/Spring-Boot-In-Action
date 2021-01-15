package org.example.jpa.multipledb.dao.user;


import org.example.jpa.multipledb.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}