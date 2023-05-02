package com.portfolio.designPortfolio.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nume;
    private String prenume;
    private String nrTelefon;
    private String email;
    private int nrProiecteFinalizate;
    private double valoareProiecteFinalizate;

}
