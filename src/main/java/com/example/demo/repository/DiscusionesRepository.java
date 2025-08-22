package com.example.demo.repository;

import com.example.demo.model.Discusiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscusionesRepository extends JpaRepository<Discusiones, Long> {
}