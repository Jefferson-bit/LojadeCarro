package com.crash.repositories;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crash.domain.FileDB;

@Repository
public interface FileRepository extends JpaRepository<FileDB, String> {
	
	@Transactional(readOnly = true)
	FileDB findByNomeIgnoreCase(String name);

}
