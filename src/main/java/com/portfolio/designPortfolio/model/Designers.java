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
    private String Nume;
    private String Prenume;
    private String Email;
    private String Description;
    private String abilitati;
    private int experience;


}
