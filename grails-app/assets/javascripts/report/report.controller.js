// wrapped
'use strict';

angular.module('gmines.report')
    .controller('ReportController', ReportController);

function ReportController(userData, $http, $scope, $cookieStore, ngTableParams, uiFieldState) {
    var vm = this;

    var o = 992;

    vm.fullName = userData.fullName;

    list();

    $scope.list = list;
    uiFieldState.uiObject.data = userData.fullName;

    function list() {
        $http.get("/game/list").success(function(data) {
            vm.games = data.games;

            $scope.tableParams = new ngTableParams({
                page: 1,
                count: 10
            }, {
                total: vm.games.length,
                getData: function($defer, params) {
                    $defer.resolve(vm.games.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            })
        });
    }

    $scope.newGame = function() {
        $http.post("/game/save", {
            user: vm.fullName
        }).success(function(data) {
            list();
        })
    }
}