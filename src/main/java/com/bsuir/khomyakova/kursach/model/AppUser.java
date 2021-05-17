package com.bsuir.khomyakova.kursach.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Entity
    @Table(name="users")
    public class AppUser implements UserDetails {
        @Id
       @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(updatable=false, nullable=false)
        private Integer userId;

        @Column(nullable = false, unique = true, length = 45)
        private String email;

        @Column(nullable = false,length = 30)
        private String password;

        @Enumerated(EnumType.STRING)
        private AppUserRole role;

   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientId_fk")
    private Client client;


   public AppUser(String password,
                  String email,
                  AppUserRole appUserRole){
       this.password=password;
       this.email=email;
       this.role=appUserRole;
   }

    public AppUser() {

    }

    public void setClient(Client client){
       this.client=client;
   }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRole getRole() {
        return role;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }

    /*
    UserDetails methods
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(AppUser.builder().email);
//        return Collections.singletonList(authority);
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
        //return null;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isPresent() {
        return true;
    }
}

