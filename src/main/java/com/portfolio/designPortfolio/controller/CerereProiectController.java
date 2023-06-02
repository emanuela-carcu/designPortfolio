package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.CerereProiect;
import com.portfolio.designPortfolio.model.CreateCerereRequestDTO;
import com.portfolio.designPortfolio.repo.CerereProiectRepo;
import com.portfolio.designPortfolio.services.CerereProiectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cereri-proiect")
public class CerereProiectController {
    @Autowired
    public CerereProiectRepo cerereProiectRepo;

    @Autowired
    public CerereProiectService cerereProiectService;

    @PostMapping("create")
    public CerereProiect createCerereProiect(@RequestBody CreateCerereRequestDTO createCerereRequestDTO) {
        return cerereProiectService.createCerereProiect(createCerereRequestDTO);
    }

    @GetMapping()
    public List<CerereProiect> getCereriProiect() {
        return cerereProiectRepo.findAll();
    }

    @GetMapping("/{id}")
    public CerereProiect getCerereProiectById(@PathVariable Long id) {
        return cerereProiectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cerere proiect not found with id: " + id));
    }

    @PutMapping("/{id}")
    public CerereProiect updateCerereProiect(@PathVariable Long id, @RequestBody CerereProiect cerereProiectDetails) {
        CerereProiect cerereProiect = cerereProiectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cerere proiect not found with id: " + id));

        cerereProiect.setClient(cerereProiectDetails.getClient());
        cerereProiect.setDesigner(cerereProiectDetails.getDesigner());
        cerereProiect.setService(cerereProiectDetails.getService());


        return cerereProiectRepo.save(cerereProiect);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCerereProiect(@PathVariable Long id) {
        cerereProiectRepo.deleteById(id);
    }


}
