package com.portfolio.designPortfolio.repo;

import com.portfolio.designPortfolio.model.CerereProiect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CerereProiectRepo extends JpaRepository<CerereProiect, Long> {
}
