'use strict';

var hangmanWebApp = angular.module('hangmanWebApp', ['ui.router']);

var app = hangmanWebApp.config(function($httpProvider, $stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider.state('home', {
        url: '/',
        views: {
            'mainView': {
                controller: 'IndexController',
                templateUrl: '/static/templates/home.html'
            }
        }
    });

    $stateProvider.state('game', {

        url: '/game/:gameId',
        views : {
            'mainView': {
                controller: 'GameController',
                templateUrl: '/static/templates/game.html'
            }
        }
    });


    
});