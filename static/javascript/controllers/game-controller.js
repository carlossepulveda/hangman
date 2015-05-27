'use strict';

hangmanWebApp.controller('GameController', ['$scope', '$state', '$stateParams', 'GameService', function($scope, $state, $stateParams, GameService) {

    var gameId = $stateParams.gameId;
    $scope.char = '';
    $scope.guess = function(char) {
        $scope.char = '';
        GameService.guessChar(gameId, char, onGuess, onError);
    }

    function onGuess(response) {
        $scope.char = '';
        $scope.game = response.data.game;
    }

    function onSuccessLoad(response) {
        $scope.char = '';
        $scope.game = response.data;
    }

    function onError() {
        $scope.char = '';
        alert('UnExpected error');
    }

    GameService.getGame(gameId, onSuccessLoad, onError);
}]);