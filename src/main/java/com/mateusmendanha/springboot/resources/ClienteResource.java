package com.mateusmendanha.springboot.resources;


import com.mateusmendanha.springboot.domain.Cliente;
import com.mateusmendanha.springboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")

public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)

    public ResponseEntity<?> encontrar (@PathVariable Integer id) {

        Cliente obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);

    }
}
