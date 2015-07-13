//= wrapped
'use strict';

angular
    .module('gmines.report')
    .config(configureRoutes);

function configureRoutes($stateProvider, roles) {
    $stateProvider
        .state('report', {
            url: '/report',
            controller: 'ReportController',
            controllerAs: 'vm',
            templateUrl: '/report/report.htm',
            data: {
                requiredRoles: [roles.USER]
            }
        })
}