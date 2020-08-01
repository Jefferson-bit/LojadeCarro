package com.crash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crash.domain.Detalhes;

@Repository
public interface DetalhesRepository extends JpaRepository<Detalhes, Integer> {

}
