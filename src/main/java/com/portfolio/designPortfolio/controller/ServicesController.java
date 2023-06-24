package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Services;
import com.portfolio.designPortfolio.repo.ServicesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
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

    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable Long id) {
        return servicesRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Services updateService(@PathVariable Long id, @RequestBody Services updatedService) {
        Services service = servicesRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));

        service.setService_name(updatedService.getService_name());
        service.setDescription(updatedService.getDescription());
        service.setFee(updatedService.getFee());
        service.setCurrency(updatedService.getCurrency());
        service.setDuration(updatedService.getDuration());
        //other properties we want to update

        return servicesRepo.save(service);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public void deleteServices(@PathVariable Long id) {
        servicesRepo.deleteById(id);
    }
}
