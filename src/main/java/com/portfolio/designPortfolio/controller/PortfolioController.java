package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Portfolio;
import com.portfolio.designPortfolio.repo.PortfolioRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    public PortfolioRepo portfolioRepo;

    @PostMapping("create")
    public Portfolio createPortfolio (@RequestBody Portfolio project){

        return portfolioRepo.save(project);
    }


    @GetMapping()
    public List<Portfolio> projects(){

        return portfolioRepo.findAll();
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public void deleteDesigners(@PathVariable Long id) {
        portfolioRepo.deleteById(id);
    }
}

