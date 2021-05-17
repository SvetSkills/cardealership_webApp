package com.bsuir.khomyakova.kursach.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer clientId;

    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

   @OneToOne(mappedBy="client"/*,cascade = CascadeType.ALL*/)
    private AppUser user;

    public void setUser(AppUser user) {
        this.user = user;
    }
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

   /* public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }*/
}
