package com.app.campapp.model;

import javax.persistence.*;

@Entity
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "camper1", nullable = false)
    private Long idCamper1;

    @Column(name = "camper2", nullable = false)
    private Long idCamper2;

    public Following() {
    }

    public Following(Long idCamper1, Long idCamper2) {
        this.idCamper1 = idCamper1;
        this.idCamper2 = idCamper2;
    }

    public Following(Long id, Long idCamper1, Long idCamper2) {
        this.id = id;
        this.idCamper1 = idCamper1;
        this.idCamper2 = idCamper2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCamper1() {
        return idCamper1;
    }

    public void setIdCamper1(Long idCamper1) {
        this.idCamper1 = idCamper1;
    }

    public Long getIdCamper2() {
        return idCamper2;
    }

    public void setIdCamper2(Long idCamper2) {
        this.idCamper2 = idCamper2;
    }
}
