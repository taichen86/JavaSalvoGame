package com.ubiqum.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    public long getSalvoID(){ return id; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayer_id")
    private GamePlayer gameplayer;
    public GamePlayer getGamePlayer(){ return gameplayer; }
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


    private int turnNumber;
    public void setTurnNumber(int number){ turnNumber = number; }
    public int getTurnNumber(){ return turnNumber; }

    public Salvo(){}

    public Salvo( int turn, GamePlayer gp, List<String> locations ){
        setTurnNumber( turn );
        setGamePlayer( gp );
        setLocations( locations );
    }

}
