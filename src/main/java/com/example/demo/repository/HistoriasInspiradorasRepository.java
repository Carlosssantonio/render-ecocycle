package com.example.demo.repository;

import com.example.demo.model.HistoriasInspiradoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriasInspiradorasRepository extends JpaRepository<HistoriasInspiradoras, Long> {
}