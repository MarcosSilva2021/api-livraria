package com.devm.api_livraria.conf;

import com.devm.api_livraria.entity.Autor;
import com.devm.api_livraria.entity.Livro;
import com.devm.api_livraria.repository.AutorRepository;
import com.devm.api_livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    // povoa o banco com objetos de autor e livro
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void run(String... args) throws Exception {

        // Verificando se os autores já existem para evitar duplicação
        if (autorRepository.count() == 0) {
            Autor a1 = new Autor(null, "David Autos");
            Autor a2 = new Autor(null, "David Baixos");

            autorRepository.saveAll(Arrays.asList(a1, a2));

            // Criando livros para os autores
            Livro l1 = new Livro(null, "O Mágico de Oz", a1);
            Livro l2 = new Livro(null, "Código Limpo", a2);

            livroRepository.saveAll(Arrays.asList(l1, l2));
        }
    }
}
