package com.crash.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crash.domain.Categoria;
import com.crash.domain.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Veiculo obj INNER JOIN obj.categorias cat WHERE obj.modelo LIKE %:modelo% AND cat IN :categorias")
	Page<Veiculo> search(@Param("modelo")String modelo, @Param("categorias") List<Categoria> categorias, Pageable pgRequest);
	
	@Transactional(readOnly = true)
	//usando padrão de nomes do spring data
	Page<Veiculo> findDistinctByModeloIgnoreCaseContainingAndCategoriasIn(@Param("modelo")String modelo, @Param("categorias") List<Categoria> categorias, Pageable pgRequest);
	

}
