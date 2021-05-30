package com.bsuir.khomyakova.kursach.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn (name = "user_id", referencedColumnName = "userId")
   private AppUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.EAGER)
    private Collection<Order> order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "comment_id", referencedColumnName = "commentId")
    private Comment comment;

    public Collection<Order> getOrder() {
        return order;
    }

    public void setOrder(Collection<Order> order) {
        this.order = order;
    }



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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
