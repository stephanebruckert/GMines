// wrapped
'use strict';

angular.module('gmines.report', ['ngTable'])
    .controller('ReportController', ReportController);

function ReportController(userData, $http, $scope, ngTableParams) {
    var vm = this;

    vm.fullName = userData.fullName;

    $http.get("/game/angular").success( function( data ) {
    	vm.games = data.games;

	    $scope.tableParams = new ngTableParams({
	        page: 1,            // show first page
	        count: 10           // count per page
	    }, {
	        total: vm.games.length, // length of data
	        getData: function ($defer, params) {
	            $defer.resolve(vm.games.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	        }
	    })
    });
}
