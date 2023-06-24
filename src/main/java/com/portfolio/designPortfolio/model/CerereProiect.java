package com.portfolio.designPortfolio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cerere_proiecte")
@Getter
@Setter
public class CerereProiect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "designer_id")
    private User designer;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;


}
