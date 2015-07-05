// wrapped
'use strict';

angular.module('gmines.report')
    .controller('ReportController', ReportController);

function ReportController(userData, $http, $scope, ngTableParams) {
    var vm = this;

    vm.fullName = userData.fullName;

    list();

	$scope.list = list;

	function list() {
	    $http.get("/game/list").success( function( data ) {
	    	vm.games = data.games;

		    $scope.tableParams = new ngTableParams({
		        page: 1,
		        count: 10 
		    }, {
		        total: vm.games.length,
		        getData: function ($defer, params) {
		            $defer.resolve(vm.games.slice((params.page() - 1) * params.count(), params.page() * params.count()));
		        }
		    })
	    });
	}

    $scope.newGame = function() {
        $http.post("/game/save").success( function( data ) {
        	$scope.tableParams.total(data.length);
            list();
        })
    }
}
