package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Clients;
import com.portfolio.designPortfolio.repo.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    @PutMapping("{id}")
    public Clients updateClient(@PathVariable long id, @RequestBody Clients clientDetails) {
        Clients updateClient = clientsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

        updateClient.setNume(clientDetails.getNume());
        updateClient.setPrenume(clientDetails.getPrenume());
        updateClient.setEmail(clientDetails.getEmail());
        updateClient.setNrProiecteFinalizate(clientDetails.getNrProiecteFinalizate());
        updateClient.setValoareProiecteFinalizate(clientDetails.getValoareProiecteFinalizate());

        return clientsRepo.save(updateClient);
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


