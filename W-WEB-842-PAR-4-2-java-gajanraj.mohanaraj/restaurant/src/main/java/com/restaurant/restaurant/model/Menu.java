package com.restaurant.restaurant.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameMenu;

    private String entree;

    @Column(columnDefinition = "TEXT")
    private String plats;

    private String desserts;

    @Column(nullable = false, unique = true)
    private String tokenMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    private FirstConfiguration firstConfiguration;

    public Menu() {
    }

    public Menu(String nameMenu, String entree, String plats, String desserts, FirstConfiguration firstConfiguration) {
        this.nameMenu = nameMenu;
        this.entree = entree;
        this.plats = plats;
        this.desserts = desserts;
        this.firstConfiguration = firstConfiguration;
        this.tokenMenu = UUID.randomUUID().toString();
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public String getEntree() {
        return entree;
    }

    public void setEntree(String entree) {
        this.entree = entree;
    }

    public String getPlats() {
        return plats;
    }

    public void setPlats(String plats) {
        this.plats = plats;
    }

    public String getDesserts() {
        return desserts;
    }

    public void setDesserts(String desserts) {
        this.desserts = desserts;
    }

    public String getTokenMenu() {
        return tokenMenu;
    }

    public void setTokenMenu(String tokenMenu) {
        this.tokenMenu = tokenMenu;
    }

    public FirstConfiguration getFirstConfiguration() {
        return firstConfiguration;
    }

    public void setFirstConfiguration(FirstConfiguration firstConfiguration) {
        this.firstConfiguration = firstConfiguration;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", nameMenu='" + nameMenu + '\'' +
                ", entree='" + entree + '\'' +
                ", plats='" + plats + '\'' +
                ", desserts='" + desserts + '\'' +
                ", tokenMenu='" + tokenMenu + '\'' +
                ", firstConfiguration=" + firstConfiguration +
                '}';
    }
}