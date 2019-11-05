

var vm = new Vue({
    el: '#vue-instance',
    data: {
        games: [],
        rows: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        columns: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
        gameplayerID: 0,
        gameID: 0,
        playerID: 0, 
        gameplayers: [],
        ships: [],
        shipLocations: [],
        salvoes: [],
        leaderboardData: {},
        leaderboardDisplayData: [{}] // show this


    }
})

// getGameView();

function getGameView()
{
    console.log( location.search );
    console.log( paramObj( location.search ))
    const gp = paramObj( location.search ).gp || "1";
    console.log( "game view for gp ... " + gp );
    vm.gameplayerID = gp;
    console.log( vm.gameplayerID );

    const url = "http://localhost:8080/api/game_view/" + gp;
    console.log( url );
    fetch( url ).then( function(response){
        console.log( "got response: " + response );
        if( response.ok ){
            return response.json();
        }

    } ).then( function( json ){
        console.log( "got json: " + json );
         var data = json;
        console.log( data );

        vm.gameID = data.id;
        console.log( "game id: " + vm.gameID );


        data.gameplayers.forEach( gp => {
            console.log( gp );
            vm.gameplayers.push( gp );
            if( gp.id == vm.gameplayerID ){
                vm.playerID = gp.player.id;
                console.log( "player ID ---> " + vm.playerID );
            }
         });      
         data.ships.forEach( ship => {
             console.log( ship );
             vm.ships.push( ship );
         }); 
         console.log( vm.ships );

         data.salvoes.forEach( salvo => {
            console.log( salvo );
            vm.salvoes.push( salvo );
         });
         console.log( vm.salvoes );

         colorInShipCells();
         colorInSalvoCells();


    }).catch( function( error ){
        console.log( "error: " + error );
    })
}

function colorInShipCells(){
    console.log( "color in ship cells " + vm.ships );
    vm.ships.forEach( ship => {
        ship.locations.forEach( location => {
            console.log( "location: " + location );
            vm.shipLocations.push( location );
            document.getElementById( location ).style.backgroundColor = "blue";
        });
    });
    console.log( "all ship locs: " + vm.shipLocations );
}

function colorInSalvoCells(){
    console.log( "color in salvo cells " + vm.salvoes );
    vm.salvoes.forEach( salvo => {
        salvo.locations.forEach( location => {
            const id = "s"+location;
            const cell = document.getElementById( id );
            cell.appendChild( document.createTextNode( salvo.turn ) );
            cell.style.backgroundColor = cell.style.backgroundColor = "green";

            // check if hit by opponent
            if( salvo.player != vm.playerID ){
                cell.style.backgroundColor = "yellow";
                if( vm.shipLocations.includes( location )){
                    cell.style.backgroundColor = "orange";
                }
            }
        
        });
    });
}

function paramObj(search) {
    var obj = {};
    var reg = /(?:[?&]([^?&#=]+)(?:=([^&#]*))?)(?:#.*)?/g;
  
    search.replace(reg, function(match, param, val) {
      obj[decodeURIComponent(param)] = val === undefined ? "" : decodeURIComponent(val);
    });
  
    return obj;
  }



//----- leaderboard -----//
getLeaderboardData();
function getLeaderboardData(){
    console.log( "doing leaderboard..." );
    const url = "http://localhost:8080/api/leaderboard";
    fetch( url ).then( function(response){
        console.log( "got response: " + response );
        if( response.ok ){
            return response.json();
        }
    }).then( function(json){
        console.log( "got json: " + json);
        console.log( json );
        vm.leaderboardData = json;
        console.log( vm.leaderboardData );
        vm.leaderboardData.players.forEach( player => {
            console.log( "doing players ... " + player );
            let total =0;
            let wins =0;
            let losses = 0;
            let ties = 0;
            
             player.scores.forEach( score => {
                 if( score.score > 0.5 ){
                    wins++;
                 }else if( score.score > 0.0 ){
                    ties++;
                 }else{
                     losses++;
                 }
                 total = total + score.score;
             } );
             const data = { 
                email: player.email,
                total: total,
                wins: wins,
                losses: losses,
                ties: ties
             }
             console.log( data );

             vm.leaderboardDisplayData.push( data );
        } );

    })

}
