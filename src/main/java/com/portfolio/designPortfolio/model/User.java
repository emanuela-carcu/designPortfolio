package com.portfolio.designPortfolio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String description;

    @Column
    private boolean skills_ui_ux;

    @Column
    private boolean skills_ar_vr;

    @Column
    private boolean skills_digital;

    @Column
    private boolean skills_animation;

    @Column
    private boolean skills_film_editing;

    @Column
    private boolean skills_visual_effects;

    @Column
    private boolean skills_three_motion;

    @Column
    private boolean skills_three_art;

    private String phoneNumber;

    private int experience;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // Add getters and setters
}
