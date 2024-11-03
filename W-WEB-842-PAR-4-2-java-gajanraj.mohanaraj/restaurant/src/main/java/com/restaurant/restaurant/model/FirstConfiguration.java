package com.restaurant.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class FirstConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String colorTitle;
    private String colorButton;
    private String menu;
    private String password;
    private String token;
    
    @Column(columnDefinition = "int default 0")
    private int card;

    @Column(columnDefinition = "int default 0")
    private int is_menu;

    @Column(columnDefinition = "int default 0")
    private int is_plats; 

    @Lob
    @Column(name = "photo", columnDefinition="LONGBLOB")
    private byte[] photo;

    public FirstConfiguration() {}

    public FirstConfiguration(String name, String url, String colorTitle, String colorButton, String menu, String password, String token, byte[] photo) {
        this.name = name;
        this.url = url;
        this.colorTitle = colorTitle;
        this.colorButton = colorButton;
        this.menu = menu;
        this.password = password;
        this.token = token;
        this.photo = photo;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColorTitle() {
        return colorTitle;
    }

    public void setColorTitle(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    public String getColorButton() {
        return colorButton;
    }

    public void setColorButton(String colorButton) {
        this.colorButton = colorButton;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public int getIs_menu() {
        return is_menu;
    }

    public void setIs_menu(int is_menu) {
        this.is_menu = is_menu;
    }

    public int getIs_plats() {
        return is_plats;
    }

    public void setIs_plats(int is_plats) {
        this.is_plats = is_plats;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
