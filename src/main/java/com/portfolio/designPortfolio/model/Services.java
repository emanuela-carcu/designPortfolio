package com.portfolio.designPortfolio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private String description;
    private Long fee;
    private String currency;
    private int duration;

    @ManyToOne
    @JoinColumn(name="designer_id")
    private Designers designer;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Clients client;
}
