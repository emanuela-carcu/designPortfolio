package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Designers;
import com.portfolio.designPortfolio.repo.DesignersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("delete/{id}")
    @Transactional
    public void deleteDesigners(@PathVariable Long id) {
        designersRepo.deleteById(id);
    }
}
