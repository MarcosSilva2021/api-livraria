package com.devm.api_livraria.service;

import com.devm.api_livraria.entity.Livro;
import com.devm.api_livraria.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro create(Livro livro){
        return repository.save(livro);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public Livro getId(Long id){
        Optional<Livro> obj = repository.findById(id);
        if (obj.isEmpty()){
            throw new EntityNotFoundException("Livro de Id"+id+" não encontrado ;-(");
        }
        return obj.get();
    }

    public List<Livro> getAll(){
        return repository.findAll();
    }

    public Livro update(Livro obj){
        Optional<Livro> newObj = repository.findById(obj.getId());
        if (newObj.isEmpty()){
            throw new EntityNotFoundException("Livro de ID"+obj.getId()+" não encontrado ;-(");
        }
        updateLivro(newObj, obj);
        return repository.save(newObj.get());

    }

    private void updateLivro(Optional<Livro> newObj, Livro obj){
        newObj.get().setNome(obj.getNome());
    }
}
