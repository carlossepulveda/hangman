'use strict';

hangmanWebApp.service("GameService", function ($http, $q) {

    function getGames(handleSuccess, handleError) {

        var request = $http({
            method: "GET",
            url: "/games"
        });

        return(request.then(handleSuccess, handleError));

    }

    function getGame(gameId, handleSuccess, handleError) {

        var request = $http({
            method: "GET",
            url: "/games/" + gameId
        });

        return(request.then(handleSuccess, handleError));

    }

    function createGame(handleSuccess, handleError) {
        var request = $http({
            method: "POST",
            url: "games"
        });

        return(request.then(handleSuccess, handleError));
    }

    function guessChar(gameId, char_, handleSuccess, handleError) {
        var request = $http({
            method: "POST",
            url: "/games/" + gameId,
            data: {
                char_: char_
            }
        });

        return(request.then(handleSuccess, handleError));
    }
    return({
        getGames: getGames,
        getGame: getGame,
        createGame: createGame,
        guessChar: guessChar
    });


}
);