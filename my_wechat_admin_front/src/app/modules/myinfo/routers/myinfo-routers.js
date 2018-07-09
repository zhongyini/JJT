'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('main.myinfo', {
        template: '<div ui-view></div>',
        url: '/',
        abstract: true
    })
    .state('main.myinfo.changepassord', {
        url: '/updatePassword',
        templateUrl: 'myinfo/views/myinfo-changepassword.html',
        controller: 'MyinfoChangePassword as vm',
    })
    .state('main.myinfo.detail', {
        url: '/:id/detail',
        templateUrl: 'myinfo/views/myinfo-detail.html',
        controller: 'MyinfoDetail as vm',
    })
    .state('main.myinfo.edit', {
        url: '/:id/edit',
        templateUrl: 'myinfo/views/myinfo-edit.html',
        controller: 'MyinfoEdit as vm',
    });
};
