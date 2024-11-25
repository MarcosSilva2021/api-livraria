package com.devm.api_livraria.conf;

import com.devm.api_livraria.entity.Autor;
import com.devm.api_livraria.entity.Livro;
import com.devm.api_livraria.repository.AutorRepository;
import com.devm.api_livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {
    // povoa o banco com objetos de autor e livro
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void run(String... args) throws Exception {

        Autor a1 = new Autor(null, "David Autos");
        Autor a2 = new Autor(null, "David Baixos");

        autorRepository.saveAll(Arrays.asList(a1, a2));

        Livro l1 = new Livro(null, "O mágico de OZ", a1);
        Livro l2 = new Livro(null, "Codigo Limpo", a2);

        livroRepository.saveAll(Arrays.asList(l1, l2));
    }
}
