//= wrapped
'use strict';

angular
    .module('gmines.session')
    .config(configureRoutes);

function configureRoutes($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('login', {
            url: '/login',
            controller: 'LoginController',
            controllerAs: 'vm',
            templateUrl: '/session/login.htm'
        });
}


