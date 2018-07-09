'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('main.csv', {
        template: '<div ui-view></div>',
        url: '/csv',
        abstract: true
    })
    .state('main.csv.upload', {
        url: '/',
        templateUrl: 'csv/views/membership-new.html',
        controller: 'MembershipNewCtrl as vm',
    });    
};
