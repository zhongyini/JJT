'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('login', {
    	url: '/login',
        templateUrl: 'session/views/login.html',
        controller:  'SessionLoginCtrl as vm',
        isAnno: true
    }).state('password', {
    	url: '/updateOldDatePassword',
        templateUrl: 'session/views/update-password.html',
        controller:  'SessionPasswordCtrl as vm',
        isAnno: true
    });
};
