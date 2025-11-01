package com.example.excaption.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.excaption.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}