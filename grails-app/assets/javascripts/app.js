//= require login/module

(function(){
  'use strict';

  var app = angular.module('gmines', ['ui.router', 'ui.bootstrap', 'ngAnimate', 'gmines.login']);
  
  app.config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise('/login');
    
    $stateProvider
      .state('login', {
        url: '/login',
        controller: 'LoginController',
        templateUrl: '/login/login.htm'
      });
  });

})();