package com.example.batch_samples.batch0.repository;

import com.example.batch_samples.batch0.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
