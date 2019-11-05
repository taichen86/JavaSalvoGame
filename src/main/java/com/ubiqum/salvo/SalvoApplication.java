package com.ubiqum.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerrepo,
									  GameRepository gamerepo,
									  GamePlayerRepository gameplayerrepo,
									  ShipRepository shiprepo,
									  SalvoRepository salvorepo,
									  ScoreRepository scorerepo) {
		return (args) -> {

			// create players
			Player p1 = new Player("j.bauer@ctu.gov");
			Player p2 = new Player("c.obrian@ctu.gov");
			Player p3 = new Player("t.almeida@ctu.gov");
			Player p4 = new Player("kim_bauer@gmail.gov");
			System.out.println( "do... " + p1 );

			p1.setPassword( "password01" );
			p2.setPassword( "password02" );
			p3.setPassword( "password03" );
			p4.setPassword( "password04" ); // need to save repos again?

			playerrepo.save( p1 );
			playerrepo.save( p2 );
			playerrepo.save( p3 );
			playerrepo.save( p4 );



			// create games with dates
			Game g1 = new Game( "02-17-2018 15:20:15" );
			Game g2 = new Game( "02-17-2018 16:20:15" );
			Game g3 = new Game( "02-17-2018 17:20:15" );
			Game g4 = new Game( "02-17-2018 18:20:15" );
			Game g5 = new Game( "02-17-2018 19:20:15" );
			Game g6 = new Game( "02-17-2018 20:20:15" );
			Game g7 = new Game( "02-17-2018 21:20:15" );
			Game g8 = new Game( "02-17-2018 22:20:15" );


			gamerepo.save( g1 );
			gamerepo.save( g2 );
			gamerepo.save( g3 );
			gamerepo.save( g4 );
			gamerepo.save( g5 );
			gamerepo.save( g6 );
			gamerepo.save( g7 );
			gamerepo.save( g8 );

			// create gameplayers with ships and salvoes

			// ---- GAMEPLAYER1 GAME1 JACK BAUER --- //
			GamePlayer gp1 = new GamePlayer( g1, p1 );
			gameplayerrepo.save( gp1 );

			Ship ship01 = new Ship();
			ship01.setType(Ship.Type.DESTROYER);
			ship01.setLocations( Arrays.asList( new String[]{"H2","H3","H4"}) );
			ship01.setGamePlayer( gp1 );
			shiprepo.save( ship01 );

			Ship ship02 = new Ship();
			ship02.setType(Ship.Type.SUBMARINE);
			ship02.setLocations( Arrays.asList( new String[] {"E1", "F1", "G1"}));
			ship02.setGamePlayer( gp1 );
			shiprepo.save( ship02 );

			Ship ship03 = new Ship();
			ship03.setType(Ship.Type.PATROL_BOAT);
			ship03.setLocations( Arrays.asList( new String[] {"B4", "B5"}));
			ship03.setGamePlayer( gp1 );
			shiprepo.save( ship03 );


			Salvo salvo01 = new Salvo( 1, gp1, Arrays.asList( new String[]{"B5", "C5", "F1" } ) );
			Salvo salvo02 = new Salvo( 2, gp1, Arrays.asList( new String[]{"F2", "D5" } ) );
			salvorepo.save( salvo01 );
			salvorepo.save( salvo02 );



			GamePlayer gp2 = new GamePlayer( g1, p2 );
			gameplayerrepo.save( gp2 );


			Ship ship04 = new Ship();
			ship04.setType(Ship.Type.DESTROYER);
			ship04.setLocations( Arrays.asList( new String[] {"B5", "C5", "D5"}));
			ship04.setGamePlayer( gp2 );
			shiprepo.save( ship04 );


			Ship ship05 = new Ship();
			ship05.setType(Ship.Type.PATROL_BOAT);
			ship05.setLocations( Arrays.asList( new String[] {"F1", "F2"}));
			ship05.setGamePlayer( gp2 );
			shiprepo.save( ship05 );


			Salvo salvo03 = new Salvo( 1, gp2, Arrays.asList( new String[]{ "B4", "B5", "B6" } ) );
			Salvo salvo04 = new Salvo( 2, gp2, Arrays.asList( new String[]{ "E1", "H3", "A2" } ) );
			salvorepo.save( salvo03 );
			salvorepo.save( salvo04 );



			GamePlayer gp3 = new GamePlayer( g2, p1 );
			gameplayerrepo.save( gp3 );


			Ship ship06 = new Ship();
			ship06.setType(Ship.Type.DESTROYER);
			ship06.setLocations( Arrays.asList( new String[] {"B5", "C5", "D5"}));
			ship06.setGamePlayer( gp3 );
			shiprepo.save( ship06 );


			Ship ship07 = new Ship();
			ship07.setType(Ship.Type.PATROL_BOAT);
			ship07.setLocations( Arrays.asList( new String[] {"C6", "C7"}));
			ship07.setGamePlayer( gp3 );
			shiprepo.save( ship07 );


			Salvo salvo05 = new Salvo( 1, gp3, Arrays.asList( new String[]{ "A2", "A4", "G6" } ) );
			Salvo salvo06 = new Salvo( 2, gp3, Arrays.asList( new String[]{ "A3", "H6" } ) );
			salvorepo.save( salvo05 );
			salvorepo.save( salvo06 );



			GamePlayer gp4 = new GamePlayer( g2, p2 );
			gameplayerrepo.save( gp4 );


			Ship ship08 = new Ship();
			ship08.setType(Ship.Type.SUBMARINE);
			ship08.setLocations( Arrays.asList( new String[] {"A2", "A3", "A4"}));
			ship08.setGamePlayer( gp4 );
			shiprepo.save( ship08 );


			Ship ship09 = new Ship();
			ship09.setType(Ship.Type.PATROL_BOAT);
			ship09.setLocations( Arrays.asList( new String[] {"G6", "H6"}));
			ship07.setGamePlayer( gp4 );
			shiprepo.save( ship09 );


			Ship ship10 = new Ship();
			ship10.setType(Ship.Type.DESTROYER);
			ship10.setLocations( Arrays.asList( new String[] {"B5", "C5", "D5"}));
			ship10.setGamePlayer( gp4 );
			shiprepo.save( ship10 );


			Ship ship11 = new Ship();
			ship11.setType(Ship.Type.PATROL_BOAT);
			ship11.setLocations( Arrays.asList( new String[] {"C6", "C7"}));
			ship11.setGamePlayer( gp4 );
			shiprepo.save( ship11 );


			Salvo salvo07 = new Salvo( 1, gp4, Arrays.asList( new String[]{ "B5", "D5", "C7" } ) );
			Salvo salvo08 = new Salvo( 2, gp4, Arrays.asList( new String[]{ "C5", "C6" } ) );
			salvorepo.save( salvo07 );
			salvorepo.save( salvo08 );


			GamePlayer gp5 = new GamePlayer( g3, p2 );
			gameplayerrepo.save( gp5 );

			// TODO add ships
			Salvo salvo09 = new Salvo( 1, gp5, Arrays.asList( new String[]{ "G6", "H6", "A4" } ) );
			Salvo salvo10 = new Salvo( 2, gp5, Arrays.asList( new String[]{ "A2", "A3", "D8" } ) );
			salvorepo.save( salvo09 );
			salvorepo.save( salvo10 );


			GamePlayer gp6 = new GamePlayer( g3, p3 );
			gameplayerrepo.save( gp6 );

			Ship ship12 = new Ship();
			ship12.setType(Ship.Type.SUBMARINE);
			ship12.setLocations( Arrays.asList( new String[] {"A2", "A3", "A4"}));
			ship12.setGamePlayer( gp6 );
			shiprepo.save( ship12 );


			Ship ship13 = new Ship();
			ship13.setType(Ship.Type.PATROL_BOAT);
			ship13.setLocations( Arrays.asList( new String[] {"G6", "H6"}));
			ship13.setGamePlayer( gp6 );
			shiprepo.save( ship13 );


			Salvo salvo11 = new Salvo( 1, gp6, Arrays.asList( new String[]{ "H1", "H2", "H3" } ) );
			Salvo salvo12 = new Salvo( 2, gp6, Arrays.asList( new String[]{ "E1", "F2", "G3" } ) );
			salvorepo.save( salvo11 );
			salvorepo.save( salvo12 );




			GamePlayer gp7 = new GamePlayer( g4, p2 );
			gameplayerrepo.save( gp7 );


			Ship ship14 = new Ship();
			ship14.setType(Ship.Type.DESTROYER);
			ship13.setLocations( Arrays.asList( new String[] {"B5", "C5", "D5"}));
			ship14.setGamePlayer( gp7 );
			shiprepo.save( ship14 );


			Ship ship15 = new Ship();
			ship15.setType(Ship.Type.PATROL_BOAT);
			ship15.setLocations( Arrays.asList( new String[] {"C6", "C7"}));
			ship15.setGamePlayer( gp7 );
			shiprepo.save( ship15 );

			Salvo salvo13 = new Salvo( 1, gp7, Arrays.asList( new String[]{ "A3", "A4", "F7" } ) );
			Salvo salvo14 = new Salvo( 2, gp7, Arrays.asList( new String[]{ "A2", "G6", "H6" } ) );
			salvorepo.save( salvo13 );
			salvorepo.save( salvo14 );





			GamePlayer gp8 = new GamePlayer( g4, p1 );
			gameplayerrepo.save( gp8 );

			Ship ship16 = new Ship();
			ship16.setType(Ship.Type.SUBMARINE);
			ship16.setLocations( Arrays.asList( new String[] {"A2", "A3", "A4"}));
			ship16.setGamePlayer( gp8 );
			shiprepo.save( ship16 );


			Ship ship17 = new Ship();
			ship17.setType(Ship.Type.PATROL_BOAT);
			ship17.setLocations( Arrays.asList( new String[] {"G6", "H6"}));
			ship17.setGamePlayer( gp8 );
			shiprepo.save( ship17 );

			Salvo salvo15 = new Salvo( 1, gp8, Arrays.asList( new String[]{ "B5", "C6", "H1" } ) );
			Salvo salvo16 = new Salvo( 2, gp8, Arrays.asList( new String[]{ "C5", "C7", "D5" } ) );
			salvorepo.save( salvo15 );
			salvorepo.save( salvo16 );




			GamePlayer gp9 = new GamePlayer( g5, p3 );
			gameplayerrepo.save( gp9 );


			Ship ship18 = new Ship();
			ship18.setType(Ship.Type.DESTROYER);
			ship18.setLocations( Arrays.asList( new String[] {"B5", "C5", "D5"}));
			ship18.setGamePlayer( gp9 );
			shiprepo.save( ship18 );


			Ship ship19 = new Ship();
			ship19.setType(Ship.Type.PATROL_BOAT);
			ship19.setLocations( Arrays.asList( new String[] {"C6", "C7"}));
			ship19.setGamePlayer( gp9 );
			shiprepo.save( ship19 );

			Salvo salvo17 = new Salvo( 1, gp9, Arrays.asList( new String[]{ "A1", "A2", "A3" } ) );
			Salvo salvo18 = new Salvo( 2, gp9, Arrays.asList( new String[]{ "G6", "G7", "G8" } ) );
			salvorepo.save( salvo17 );
			salvorepo.save( salvo18 );



			// add scores
			Score score01 = new Score( g1, p1, 1.0f , "02-17-2018 15:50:15" );
			scorerepo.save( score01 );
			Score score02 = new Score( g1, p2, 0.0f , "02-17-2018 15:50:15" );
			scorerepo.save( score02 );
			Score score03 = new Score( g2, p1, 0.5f , "02-17-2018 16:50:15" );
			scorerepo.save( score03 );
			Score score04 = new Score( g2, p2, 0.5f , "02-17-2018 16:50:15" );
			scorerepo.save( score04 );

			Score score05 = new Score( g3, p2, 1.0f , "02-17-2018 17:50:15" );
			scorerepo.save( score05 );
			Score score06 = new Score( g3, p3, 0.0f , "02-17-2018 17:50:15" );
			scorerepo.save( score06 );

			Score score07 = new Score( g4, p2, 0.5f , "02-17-2018 18:50:15" );
			scorerepo.save( score07 );
			Score score08 = new Score( g4, p1, 0.5f , "02-17-2018 18:50:15" );
			scorerepo.save( score08 );

		};
	}

}
