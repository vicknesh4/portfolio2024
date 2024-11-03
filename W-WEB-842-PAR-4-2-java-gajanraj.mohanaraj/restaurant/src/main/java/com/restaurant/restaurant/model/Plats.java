package com.restaurant.restaurant.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Plats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    private String description;

    private double prix;

    private String allergenes;

    @Column(name = "is_plat", nullable = false)
    private boolean isPlat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    private FirstConfiguration firstConfiguration;

    @Column(unique = true, nullable = false)
    private String tokenPlat;

    public Plats() {
        this.tokenPlat = UUID.randomUUID().toString();
    }

    public Plats(String nom, byte[] photo, String description, double prix, String allergenes, boolean isPlat, FirstConfiguration firstConfiguration) {
        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.prix = prix;
        this.allergenes = allergenes;
        this.isPlat = isPlat;
        this.firstConfiguration = firstConfiguration;
        this.tokenPlat = UUID.randomUUID().toString();
    }

 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(String allergenes) {
        this.allergenes = allergenes;
    }

    public boolean isPlat() {
        return isPlat;
    }

    public void setPlat(boolean isPlat) {
        this.isPlat = isPlat;
    }

    public FirstConfiguration getFirstConfiguration() {
        return firstConfiguration;
    }

    public void setFirstConfiguration(FirstConfiguration firstConfiguration) {
        this.firstConfiguration = firstConfiguration;
    }

    public String getTokenPlat() {
        return tokenPlat;
    }

    public void setTokenPlat(String tokenPlat) {
        this.tokenPlat = tokenPlat;
    }

    @Override
    public String toString() {
        return "Plats{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", allergenes='" + allergenes + '\'' +
                ", isPlat=" + isPlat +
                ", firstConfiguration=" + (firstConfiguration != null ? firstConfiguration.getId() : null) +
                ", tokenPlat='" + tokenPlat + '\'' +
                '}';
    }
}
