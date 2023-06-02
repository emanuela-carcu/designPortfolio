package com.portfolio.designPortfolio.services;

import com.portfolio.designPortfolio.model.*;
import com.portfolio.designPortfolio.repo.CerereProiectRepo;
import com.portfolio.designPortfolio.repo.ClientsRepo;
import com.portfolio.designPortfolio.repo.DesignersRepo;
import com.portfolio.designPortfolio.repo.ServicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class CerereProiectService {

    @Autowired
    private CerereProiectRepo cerereProiectRepo;

    @Autowired
    private DesignersRepo designersRepo;

    @Autowired
    private ClientsRepo clientsRepo;

    @Autowired
    private ServicesRepo servicesRepo;


    public CerereProiect createCerereProiect(CreateCerereRequestDTO requestDTO) {
        Clients client = clientsRepo.findById(requestDTO.getClientId()).orElseThrow(() -> new NotFoundException("Client not found"));
        Designers designer = designersRepo.findById(requestDTO.getDesignerId()).orElseThrow(() -> new NotFoundException("Designer not found"));
        Services services = servicesRepo.findById(requestDTO.getServiceId()).orElseThrow(() -> new NotFoundException("Service not found"));

        CerereProiect cerereProiect = new CerereProiect();
        cerereProiect.setService(services);
        cerereProiect.setDesigner(designer);
        cerereProiect.setClient(client);

        return cerereProiectRepo.save(cerereProiect);
    }
}
