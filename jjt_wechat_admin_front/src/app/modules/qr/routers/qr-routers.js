'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('main.qr', {
        template: '<div ui-view></div>',
        url: '/qr',
        abstract: true
    })
    .state('main.qr.list', {
        url: '/',
        templateUrl: 'qr/views/qr-list.html',
        controller: 'QrListCtrl as vm',
    })    
    .state('main.qr.new', {
    	url: '/new',
        templateUrl: 'qr/views/qr-new.html',
        controller: 'QrNewCtrl as vm',
    })
    .state('main.qr.detail', {
        url: '/:id/detail',
        templateUrl: 'qr/views/qr-detail.html',
        controller: 'QrDetailCtrl as vm',
    })
    .state('main.qr.edit', {
        url: '/:id/edit',
        templateUrl: 'qr/views/qr-edit.html',
        controller: 'QrEditCtrl as vm',
    })
    .state('main.qr.delete', {
        url: '/:id/delete',
        templateUrl: 'qr/views/qr-detail.html',
        controller: 'QrDeleteCtrl as vm'
    });    
};
