package com.portfolio.designPortfolio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "portfolio")
@Getter
@Setter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ElementCollection
    private List<byte[]> images;

    @ManyToOne
    @JoinColumn(name="designer_id")
    private User designer;
}
