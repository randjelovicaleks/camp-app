package com.app.campapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin() {
    }

    public Admin(Long id, String name, String surname, String email, String password) {
        super(id, name, surname, email, password);
    }
}
