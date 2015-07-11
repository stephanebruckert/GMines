//= wrapped
'use strict';

angular.module('gmines.session')
    .run(initializeStateChangeListener);

initializeStateChangeListener.$inject = ['authStateChangeManager'];

function initializeStateChangeListener(authStateChangeManager) {
    authStateChangeManager.initialize();
}
