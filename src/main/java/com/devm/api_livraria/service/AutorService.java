package com.devm.api_livraria.service;

import com.devm.api_livraria.entity.Autor;
import com.devm.api_livraria.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor create(Autor autor){
        return repository.save(autor);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

    public Autor getId(Long id){
        Optional<Autor> obj = repository.findById(id);
        if (obj.isEmpty()){
            throw new EntityNotFoundException("Autor de Id"+id+" não encontrado ;-(");
        }
        return obj.get();
    }

    public List<Autor> getAll(){
        return repository.findAll();
    }

    public Autor update(Autor obj){
        Optional<Autor> newObj = repository.findById(obj.getId());
        if (newObj.isEmpty()){
            throw new EntityNotFoundException("Autor de ID"+obj.getId()+" não encontrado ;-(");
        }
        updateAutor(newObj, obj);
        return repository.save(newObj.get());

    }

    private void updateAutor(Optional<Autor> newObj, Autor obj){
        newObj.get().setNome(obj.getNome());
    }
}
