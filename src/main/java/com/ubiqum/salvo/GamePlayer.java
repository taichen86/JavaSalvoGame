package com.ubiqum.salvo;

import jdk.nashorn.internal.runtime.options.Option;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy="gameplayer", fetch=FetchType.EAGER)
    private Set<Ship> ships = new HashSet<Ship>();

    @OneToMany(mappedBy="gameplayer", fetch=FetchType.EAGER)
    private Set<Salvo> salvoes = new HashSet<Salvo>();


    private Date creationDate;

    public GamePlayer(){}

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        System.out.println("GamePlayer created " + player + " " + game );
    }

    public long getGamePlayerID(){ return id; }

    public Game getGame(){ return game; }
    public void setGame(Game game){ this.game = game; }

    public Player getPlayer(){ return player; }
    public void setPlayer(Player player){ this.player = player; }

    public Set<Ship> getShips(){ return ships; }
    public void addShip(Ship ship){
        ship.setGamePlayer( this );
        System.out.println( "add ship " + ship );
        ships.add( ship );
    }

    public Set<Salvo> getSalvoes(){ return salvoes; }
    public void addSalvo(Salvo salvo){
        salvo.setGamePlayer( this );
        salvoes.add( salvo );
    }

    public Score getScore(){
        return game.getScores()
                .stream()
                .filter( score ->
                        score.getPlayerID() == player.getPlayerID() )
                .findFirst().orElse(null);
    }




}
