/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author user
 */
public class Ticket {
    private int idTicket;
    private int idUser;
    private String description;
    private User sender;
    private User provider;
    private Date dateTicket;
    private String status;
    private String image;
    private int categ;
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Date dateTicket) {
        this.dateTicket = dateTicket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Ticket() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }

    public int getCateg() {
        return categ;
    }

    public void setCateg(int categ) {
        this.categ = categ;
    }

    public Ticket(String description, User sender, User provider, String status, String image,int categ) {
        this.description = description;
        this.sender = sender;
        this.provider = provider;
        this.status = status;
        this.image = image;
        this.categ = categ;
    }

    public Ticket(int idTicket, int idUser, String description, User sender, User provider, Date dateTicket, String status, String image, int categ) {
        this.idTicket = idTicket;
        this.idUser = idUser;
        this.description = description;
        this.sender = sender;
        this.provider = provider;
        this.dateTicket = dateTicket;
        this.status = status;
        this.image = image;
        this.categ = categ;
    } 
}
