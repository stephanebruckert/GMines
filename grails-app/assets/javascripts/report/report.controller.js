// wrapped
'use strict';

angular.module('gmines.report')
    .controller('ReportController', ReportController);

function ReportController(userData) {
    var vm = this;

    vm.fullName = userData.fullName;
}
