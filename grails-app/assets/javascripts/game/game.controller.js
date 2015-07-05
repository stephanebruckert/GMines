// wrapped
'use strict';

angular.module('gmines.game')
    .controller('gameController', gameController);

function gameController(userData, $http, $scope, ngTableParams, $stateParams) {
    var vm = this;
    vm.gameId = $stateParams.gameId;
	
	$http.get("/game/info/" + vm.gameId).success( function( data ) {
    	vm.p1 = data.p1;
    	vm.p2 = data.p2;
    	vm.who = data.who;
    	vm.p1c = data.p1c;
    	vm.p2c = data.p2c;
    	vm.winner = data.winner;
    	vm.grid = data.grid;
    });

}
