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
    private String Nume;
    private String Prenume;
    private Long NrTelefon;
    private String Email;
    private String adresaFacturare;
    private int nrProiecteFinalizate;
    private double valoareProiecteFinalizate;

}