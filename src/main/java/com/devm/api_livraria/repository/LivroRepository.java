package com.devm.api_livraria.repository;

import com.devm.api_livraria.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
