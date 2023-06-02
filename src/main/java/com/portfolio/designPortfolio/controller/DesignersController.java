package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Designers;
import com.portfolio.designPortfolio.repo.DesignersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designers")
public class DesignersController {
    @Autowired
    public DesignersRepo designersRepo;

    @PostMapping("create")
    public Designers createDesigner (@RequestBody Designers designer){

        return designersRepo.save(designer);
    }

    @GetMapping()
    public List<Designers> designers(){

        return designersRepo.findAll();
    }
    @GetMapping("{id}")
    public Designers getDesignerById(@PathVariable("id") Long id) {
        return designersRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Designer not found with id: " + id));
    }

    @PutMapping("{id}")
    public Designers updateDesigner(@PathVariable("id") Long id, @RequestBody Designers designerDetails) {
        Designers designer = designersRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Designer not found with id: " + id));

        designer.setNume(designerDetails.getNume());
        designer.setPrenume(designerDetails.getPrenume());
        designer.setEmail(designerDetails.getEmail());
        designer.setDescription(designerDetails.getDescription());
        designer.setExperience(designerDetails.getExperience());

        return designersRepo.save(designer);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public void deleteDesigners(@PathVariable Long id) {
        designersRepo.deleteById(id);
    }
}
