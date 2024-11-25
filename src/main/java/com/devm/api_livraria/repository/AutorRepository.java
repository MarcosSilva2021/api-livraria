package com.devm.api_livraria.repository;

import com.devm.api_livraria.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
