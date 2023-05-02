package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Services;
import com.portfolio.designPortfolio.repo.ServicesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Services")
public class ServicesController {
    @Autowired
    public ServicesRepo servicesRepo;

    @PostMapping("create")
    public Services createService (@RequestBody Services service){

        return servicesRepo.save(service);
    }


    @GetMapping()
    public List<Services> services(){

        return servicesRepo.findAll();
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public void deleteDesigners(@PathVariable Long id) {
        servicesRepo.deleteById(id);
    }
}
