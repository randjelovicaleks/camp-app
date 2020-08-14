package com.app.campapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "caterer_id", referencedColumnName = "id")
    private Caterer caterer;

    @OneToMany(mappedBy = "statistic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Campsite> campsites;

    public Statistic() {
    }

    public Statistic(Long id, Caterer caterer) {
        this.id = id;
        this.caterer = caterer;
        this.campsites = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Caterer getCaterer() {
        return caterer;
    }

    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
    }

    public List<Campsite> getCampsites() {
        return campsites;
    }

    public void setCampsites(List<Campsite> campsites) {
        this.campsites = campsites;
    }
}
