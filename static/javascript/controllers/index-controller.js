
'use strict';

hangmanWebApp.controller('IndexController', ['$scope', '$state', '$stateParams', 'GameService', function($scope, $state, $stateParams, GameService) {


    $scope.createGame = function() {
        GameService.createGame(onCreatedGame, onError);
    }

    $scope.viewGame = function(gameId) {
        $state.go('game', { gameId : gameId });
    }

    function onCreatedGame(response) {
        $scope.games.push(response.data);
    }

    function onGamesLoaded(response) {
        $scope.games = response.data;

    } 
    function onError(response) {
        console.log(response.data);
        alert("UnExpected Error ");
    }
    GameService.getGames(onGamesLoaded, onError);
}]);

