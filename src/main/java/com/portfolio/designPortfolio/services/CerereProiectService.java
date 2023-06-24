package com.portfolio.designPortfolio.services;

import com.portfolio.designPortfolio.model.CerereProiect;
import com.portfolio.designPortfolio.model.CreateCerereRequestDTO;
import com.portfolio.designPortfolio.model.Services;
import com.portfolio.designPortfolio.model.User;
import com.portfolio.designPortfolio.repo.CerereProiectRepo;
import com.portfolio.designPortfolio.repo.ServicesRepo;
import com.portfolio.designPortfolio.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CerereProiectService {

    @Autowired
    private CerereProiectRepo cerereProiectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ServicesRepo servicesRepo;


    public CerereProiect createCerereProiect(CreateCerereRequestDTO requestDTO) {
        User client = userRepo.findById(requestDTO.getClientId()).orElseThrow(() -> new NotFoundException("Client not found"));
        User designer = userRepo.findById(requestDTO.getDesignerId()).orElseThrow(() -> new NotFoundException("Designer not found"));
        Services services = servicesRepo.findById(requestDTO.getServiceId()).orElseThrow(() -> new NotFoundException("Service not found"));

        CerereProiect cerereProiect = new CerereProiect();
        cerereProiect.setService(services);
        cerereProiect.setDesigner(designer);
        cerereProiect.setClient(client);

        return cerereProiectRepo.save(cerereProiect);
    }

    public List<CerereProiect> getAllByClientId(Long cliendId) {
        User client = userRepo.findById(cliendId).orElseThrow(() -> new NotFoundException("Client not found"));
        return cerereProiectRepo.findCerereProiectByClient(client);
    }
}
