package com.portfolio.designPortfolio.repo;

import com.portfolio.designPortfolio.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepo extends JpaRepository<Clients, Long> {
}
