package com.underwearstore.orderservice.repository;

import com.underwearstore.orderservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
