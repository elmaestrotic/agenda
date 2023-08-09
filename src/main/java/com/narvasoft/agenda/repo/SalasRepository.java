package com.narvasoft.agenda.repo;


import com.narvasoft.agenda.model.Diarios;
import com.narvasoft.agenda.model.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalasRepository extends JpaRepository<Salas, Long> {
}