'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('main.adminManager', {
        template: '<div ui-view></div>',
        url: '/adminManager',
        abstract: true
    })
    .state('main.adminManager.list', {
        url: '/',
        templateUrl: 'admin/views/admin-list.html',
        controller: 'AdminListCtrl as vm',
    })    
    .state('main.adminManager.new', {
    	url: '/new',
        templateUrl: 'admin/views/admin-new.html',
        controller: 'AdminNewCtrl as vm',
    })
    .state('main.adminManager.detail', {
        url: '/:id/detail',
        templateUrl: 'admin/views/admin-detail.html',
        controller: 'AdminDetailCtrl as vm',
    })
    .state('main.adminManager.edit', {
        url: '/:id/edit',
        templateUrl: 'admin/views/admin-edit.html',
        controller: 'AdminEditCtrl as vm',
    })
    .state('main.adminManager.delete', {
        url: '/:id/delete',
        templateUrl: 'admin/views/admin-detail.html',
        controller: 'AdminDeleteCtrl as vm'
    });    
};
