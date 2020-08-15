package com.mateusmendanha.springboot.services;

// Class que faz o serviço de busca de categorias


import com.mateusmendanha.springboot.domain.Pedido;
import com.mateusmendanha.springboot.repositories.PedidoRepository;
import com.mateusmendanha.springboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(

                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

    }
}
