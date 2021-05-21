package com.bsuir.khomyakova.kursach.model;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

   @OneToOne(cascade = CascadeType.ALL)//cascade был закомменчен
   @JoinColumn (name = "user_id", referencedColumnName = "userId")
   private AppUser user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Car> cars;

    public void setUser(AppUser user) {
        this.user = user;
    }
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
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

    public AppUser getUser() {
        return user;
    }

//    public Collection<Car> getCarsCollection() {
//        return carsCollection;
//    }
//
//    public void setCarsCollection(Collection<Car> carsCollection) {
//        this.carsCollection = carsCollection;
//    }

//    public void setUser(AppUser user) {
//        this.user = user;
//    }
}
