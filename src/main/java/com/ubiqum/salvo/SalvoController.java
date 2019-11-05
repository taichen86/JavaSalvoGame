package com.ubiqum.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
    @Autowired PlayerRepository playerRepository;

    @RequestMapping("/games")
    public List<Object> getAllGames(){
        return gameRepository.findAll().stream().map( g -> makeGameDTO(g) ).collect(Collectors.toList());
    }

    @RequestMapping("/players")
    public List<Object> getAllPlayers(){
        return playerRepository.findAll().stream().map( p -> makePlayerDTO(p) ).collect(Collectors.toList());
    }


    @RequestMapping("/leaderboard")
    public Map<String, Object> showLeaderboard(){
        Map<String, Object> dto = new LinkedHashMap<>();
        List<Object> players = new LinkedList<>();

        playerRepository.findAll().stream().forEach( player -> players.add( makeLeaderboardDTO( player) ));
        dto.put( "players", players );
        return dto;
    }

    @RequestMapping("/game_view/{id}")
    public Map<String, Object> showGameView(@PathVariable("id") long id ) {
        System.out.println("do game view for gameplayer " + id);

        GamePlayer gameplayer = gamePlayerRepository.findById( id ).get();
        Map<String, Object> dto = makeGameDTO( gameplayer.getGame() );
        System.out.println( gameplayer.getShips() );
        dto.put( "ships", gameplayer.getShips().stream().map( ship -> makeShipDTO( ship ) ).collect(Collectors.toList()) );

        List<Object> allSalvoes = new LinkedList<>();
        Set<GamePlayer> gps = gameplayer.getGame().getGamePlayers();
        gps.forEach( gp -> {
            Set<Salvo> salvoes = gp.getSalvoes();
            salvoes.forEach( salvo -> {
                allSalvoes.add( makeSalvoDTO( salvo )  );
            } );
            //  dto.put( "salvoes", gameplayer.getSalvoes().stream().map( salvo -> makeSalvoDTO( salvo ) ).collect(Collectors.toList()) );
        } );
        dto.put( "salvoes", allSalvoes );

        return dto;
    }

    private Map<String, Object> makeShipDTO(Ship ship){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put( "type", ship.getType() );
        dto.put( "locations", ship.getLocations() );
        return dto;
    }

    private Map<String, Object> makeSalvoDTO( Salvo salvo ){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put( "player", salvo.getGamePlayer().getPlayer().getPlayerID() );
        dto.put( "turn", salvo.getTurnNumber() );
        dto.put( "locations", salvo.getLocations() );
        return dto;
    }

    private Map<String,Object> makeGameDTO(Game game){
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getGameID());
        dto.put("date", game.getCreationDate());
        dto.put("gameplayers", game.getGamePlayers()
                                    .stream()
                                    .map( gp -> makeGamePlayerDTO( gp ) )
                                    .collect((Collectors.toList())));
//        dto.put("scores", game.getScores().stream()
//                                            .map( score -> makeScoreDTO( score ))
//                                            .collect(Collectors.toList()));
        return dto;
    }

    private Set<GamePlayer> getGamePlayers(Game game){
        return game.getGamePlayers();
    }

    private Map<String, Object> makeGameViewDTO( Game game ){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getGameID());
        dto.put("date", game.getCreationDate());
        dto.put("gameplayers", game.getGamePlayers()
                .stream()
                .map( gp -> makeGamePlayerDTO( gp ) )
                .collect((Collectors.toList())));
        return dto;
    }

    private Map<String,Object> makeGamePlayerDTO(GamePlayer gameplayer){
        Map<String,Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gameplayer.getGamePlayerID());
        dto.put("player", makePlayerDTO( gameplayer.getPlayer() ) );
        // do score here
        dto.put( "score", makeScoreDTO( gameplayer.getScore() ));
        return dto;
    }

    private Map<String,Object> makePlayerDTO(Player player){
        Map<String,Object> dto = new LinkedHashMap<String, Object>();
        dto.put( "id", player.getPlayerID() );
        dto.put( "email", player.getUsername() );
        return dto;
    }

    private Map<String, Object> makeScoreDTO(Score score){
        if( score == null ){ return null; }
        Map<String,Object> dto = new LinkedHashMap<>();
//        dto.put("id", score.getID());
//        dto.put("gameID", score.getGame().getGameID());
//        dto.put("playerID", score.getPlayer().getPlayerID());
        dto.put( "score", score.getScore() );
//        dto.put( "finishDate", score.getFinishDate() );
        return dto;
    }

    private Map<String,Object> makeLeaderboardDTO(Player player){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put( "email", player.getUsername() );
        dto.put( "scores", player.getScores().stream().map( score -> makeScoreDTO(score) ).collect(Collectors.toList()) );
        return dto;
    }




}
