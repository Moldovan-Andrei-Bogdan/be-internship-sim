package com.mecorp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecorp.enums.Role;

import javax.persistence.*;

@Entity
public class Authority {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
