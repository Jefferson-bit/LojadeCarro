package com.crash.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crash.domain.Detalhes;
import com.crash.domain.Veiculo;

@Repository
public interface DetalhesRepository extends JpaRepository<Detalhes, Integer> {

	@Query("SELECT DISTINCT obj FROM Detalhes obj INNER JOIN obj.veiculos vei WHERE vei IN :veiculos")
	Page<Detalhes> search( @Param("veiculos")List<Veiculo> veiculos, Pageable pgRequest);
}
