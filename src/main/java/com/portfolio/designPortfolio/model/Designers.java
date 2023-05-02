package com.portfolio.designPortfolio.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "designers")
@Getter
@Setter
public class Designers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nume;
    private String prenume;
    private String email;
    private String descriere;
    private String abilitati;
    private int experienta;


}
