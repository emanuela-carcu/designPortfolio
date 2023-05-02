package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Clients;
import com.portfolio.designPortfolio.repo.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    public ClientsRepo clientsRepo;

    @PostMapping("create")
    public Clients createClient(@RequestBody Clients client){

        return clientsRepo.save(client);
    }

    @GetMapping()
    public List<Clients> clients() {

        return clientsRepo.findAll();
    }

    @DeleteMapping("delete/{client_id}")
    public void deleteClient(@PathVariable("client_id") Long clientId) {
        clientsRepo.deleteById(clientId);
    }

}


