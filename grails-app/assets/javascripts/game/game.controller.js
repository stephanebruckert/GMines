// wrapped
'use strict';

angular.module('gmines.game')
    .controller('gameController', gameController);

function gameController(userData, $http, $scope, ngTableParams, $stateParams, $interval) {
    var vm = this;
    vm.gameId = $stateParams.gameId;

    callAtInterval();
    $interval(callAtInterval, 1000);

    function callAtInterval() {
		$http.get("/game/info/" + vm.gameId).success(update);
	}

	$scope.play = function(num) {
		$http.post("/game/stroke", { 
			user: userData.fullName, 
			num: num, 
			gameId: vm.gameId
		}).success(update);
	}

	function update(data) {
		vm.p1 = data.p1;
    	vm.p2 = data.p2;
    	vm.who = data.who;
    	vm.p1c = data.p1c;
    	vm.p2c = data.p2c;
    	vm.winner = data.winner;
    	vm.grid = data.grid;
	}
}

