package com.portfolio.designPortfolio.repo;

import com.portfolio.designPortfolio.model.CerereProiect;
import com.portfolio.designPortfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CerereProiectRepo extends JpaRepository<CerereProiect, Long> {

    List<CerereProiect> findCerereProiectByClient(User client);
}
