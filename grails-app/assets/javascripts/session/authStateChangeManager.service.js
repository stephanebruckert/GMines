//= wrapped
'use strict';

angular.module('gmines.session')
    .factory('authStateChangeManager', authStateChangeManagerFactory);

function authStateChangeManagerFactory($rootScope, userData, $log, $state) {

    var authStateChangeManager = {
        initialize: initialize
    };

    return authStateChangeManager;

    function initialize() {
        $rootScope.$on('$stateChangeStart', investigateRoles);

        function investigateRoles(event, toState) {
            if (toState.data && toState.data.requiredRoles) {
                if (!userData.hasRoles(toState.data.requiredRoles)) {
                    $log.debug('User does not have required roles to enter this state');
                    event.preventDefault();

                    if (userData.roles.length === 0) {
                        $state.go('login');
                    }
                }
            }
        }
    }
}
