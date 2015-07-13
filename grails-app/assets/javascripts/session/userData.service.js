//= wrapped
angular.module('gmines.session')
    .factory('userData', userDataFactory);

function userDataFactory() {
    var userData = {
        userId: '',
        password: '',
        fullName: '',
        roles: [],
        clear: clear,
        hasRole: hasRole,
        hasRoles: hasRoles,
        isLoggedIn: isLoggedIn,
        populate: populate
    };

    return userData;

    function clear() {
        userData.userId = '';
        userData.password = '';
        userData.fullName = '';
        userData.roles = [];
    }

    function hasRole(role) {
        return userData.roles.indexOf(role) >= 0;
    }

    function hasRoles(roles) {
        return roles.every(userData.hasRole);
    }

    function isLoggedIn() {
        return Array.isArray(roles) && roles.length > 0;
    }

    function populate(userId, password, fullName, roles) {
        userData.userId = userId;
        userData.password = password;
        userData.fullName = fullName;
        userData.roles = Array.isArray(roles) ? roles : [];
    }
}