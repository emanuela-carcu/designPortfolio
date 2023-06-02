package com.portfolio.designPortfolio.controller;
import com.portfolio.designPortfolio.model.Designers;
import com.portfolio.designPortfolio.model.Portfolio;
import com.portfolio.designPortfolio.repo.DesignersRepo;
import com.portfolio.designPortfolio.repo.PortfolioRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    public PortfolioRepo portfolioRepo;
    @Autowired
    private DesignersRepo designersRepo;


    @PostMapping("create")
    public Portfolio createPortfolio(@RequestBody Portfolio project) {
        Designers designer = designersRepo.findById(project.getDesigner().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Designer not found with id: " + project.getDesigner().getId()));

        project.setDesigner(designer);
        return portfolioRepo.save(project);
    }



    @GetMapping()
    public List<Portfolio> projects(){

        return portfolioRepo.findAll();
    }

    @GetMapping("/{id}")
    public Portfolio getPortfolioById(@PathVariable Long id) {
        return portfolioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Portfolio updatePortfolio(@PathVariable Long id, @RequestBody Portfolio updatedPortfolio) {
        Portfolio portfolio = portfolioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id: " + id));

        Designers designer = designersRepo.findById(updatedPortfolio.getDesigner().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Designer not found with id: " + updatedPortfolio.getDesigner().getId()));

        portfolio.setDesigner(designer);
        portfolio.setTitle(updatedPortfolio.getTitle());
        portfolio.setDescription(updatedPortfolio.getDescription());
        //other properties we want to update

        return portfolioRepo.save(portfolio);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public void deleteDesigners(@PathVariable Long id) {
        portfolioRepo.deleteById(id);
    }
}

