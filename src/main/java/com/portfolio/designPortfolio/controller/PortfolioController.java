package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.Portfolio;
import com.portfolio.designPortfolio.model.User;
import com.portfolio.designPortfolio.repo.PortfolioRepo;
import com.portfolio.designPortfolio.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    public PortfolioRepo portfolioRepo;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/create")
    public Portfolio createPortfolio(@RequestParam("designerId") Long designerId,  @RequestParam("images") MultipartFile[] images,
                                    @RequestParam("title") String title, @RequestParam("description") String description) throws IOException {
        Portfolio portfolio = new Portfolio();
        User designer = userRepo.findById(designerId)
                .orElseThrow(() -> new ResourceNotFoundException("Designer not found with id: " + designerId));

        portfolio.setDesigner(designer);
        portfolio.setImages(new ArrayList<>());
        portfolio.setDescription(description);
        portfolio.setTitle(title);
        for (MultipartFile file: images) {
            portfolio.getImages().add(file.getBytes());
        }
        return portfolioRepo.save(portfolio);
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

        User designer = userRepo.findById(updatedPortfolio.getDesigner().getId())
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

