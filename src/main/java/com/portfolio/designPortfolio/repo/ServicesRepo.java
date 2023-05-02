package com.portfolio.designPortfolio.repo;

import com.portfolio.designPortfolio.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepo extends JpaRepository<Services, Long> {
}
