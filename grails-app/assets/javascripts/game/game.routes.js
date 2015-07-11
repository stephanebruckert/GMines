//= wrapped
'use strict';

angular
    .module('gmines.game')
    .config(configureRoutes);

function configureRoutes($stateProvider, roles) {
    $stateProvider
        .state('game', {
            url: '/game/:gameId',
            controller: 'gameController',
            controllerAs: 'vm',
            templateUrl: '/game/game.htm',
            data: {
                requiredRoles: [roles.USER]
            }
        });
}
