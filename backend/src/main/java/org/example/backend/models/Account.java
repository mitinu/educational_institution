package org.example.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private short accessLevel;

    public Account() {}

    public Account(String fullName, short accessLevel) {
        this.fullName = fullName;
        this.accessLevel = accessLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public short getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(short accessLevel) {
        this.accessLevel = accessLevel;
    }
}
