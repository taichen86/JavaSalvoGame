package com.ubiqum.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    public long getID(){ return id; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;
    public Game getGame(){ return game; }
    public void setGame(Game game){
        this.game = game;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;
    public Player getPlayer(){ return player; }
    public void setPlayer(Player player){
        this.player = player;
    }

    public long getPlayerID(){ return player.getPlayerID(); }

    private float score;
    public float getScore(){ return score; }
    public void setScore(float score){ this.score = score; }

    private Date finishDate;
    public Date getFinishDate(){ return finishDate; }
    public void setFinishDate(Date date){ this.finishDate = date; }

    public Score(){

    }

    public Score( Game game, Player player, float score, String dateString ){
        setGame( game );
        setPlayer( player );
        setScore( score );
        setFinishDate( getDateFromString( dateString ) );
    }

    private Date getDateFromString( String string ){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try{
            finishDate = format.parse( string );
            System.out.println("Successfully Parsed Date " + finishDate);
            return finishDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
