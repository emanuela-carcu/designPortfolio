package com.portfolio.designPortfolio.repo;

import com.portfolio.designPortfolio.model.Designers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignersRepo extends JpaRepository<Designers, Long> {
}
