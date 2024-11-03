package com.restaurant.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Entree;

    @Column(columnDefinition = "TEXT")
    private String Plats;

    private String Desserts;

    @Column() 
    private String tokenCard;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    private FirstConfiguration firstConfiguration;

    public Card() {
     
    }

    public Card(String Entree, String Plats, String Desserts, String tokenCard, FirstConfiguration firstConfiguration) {
        this.Entree = Entree;
        this.Plats = Plats;
        this.Desserts = Desserts;
        this.tokenCard = tokenCard;
        this.firstConfiguration = firstConfiguration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntree() {
        return Entree;
    }

    public void setEntree(String Entree) {
        this.Entree = Entree;
    }

    public String getPlats() {
        return Plats;
    }

    public void setPlats(String Plats) {
        this.Plats = Plats;
    }

    public String getDesserts() {
        return Desserts;
    }

    public void setDesserts(String Desserts) {
        this.Desserts = Desserts;
    }

    public String getTokenCard() {
        return tokenCard;
    }

    public void setTokenCard(String tokenCard) {
        this.tokenCard = tokenCard;
    }

    public FirstConfiguration getFirstConfiguration() {
        return firstConfiguration;
    }

    public void setFirstConfiguration(FirstConfiguration firstConfiguration) {
        this.firstConfiguration = firstConfiguration;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", Entree='" + Entree + '\'' +
                ", Plats='" + Plats + '\'' +
                ", Desserts='" + Desserts + '\'' +
                ", tokenCard='" + tokenCard + '\'' +
                ", firstConfiguration=" + firstConfiguration +
                '}';
    }
}
