package com.ubiqum.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    public long getShipID(){ return id; }

    enum Type {
        CARRIER,
        BATTLESHIP,
        SUBMARINE,
        DESTROYER,
        PATROL_BOAT
    };

    private Type type;
    public void setType(Type type){
        this.type = type;
    }

    public String getType(){ return type.name(); }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayer_id")
    private GamePlayer gameplayer;
    public void setGamePlayer(GamePlayer gameplayer){
        this.gameplayer = gameplayer;
    }

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<String>();
    public List<String> getLocations(){ return locations; }
    public void setLocations(List<String> locations){
        this.locations = locations;
    }


    public Ship(){ };



}
