package com.mateusmendanha.springboot.services;



import com.mateusmendanha.springboot.domain.Cliente;
import com.mateusmendanha.springboot.repositories.CategoriaRepository;
import com.mateusmendanha.springboot.repositories.ClienteRepository;
import com.mateusmendanha.springboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(

                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

    }
}
