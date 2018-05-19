'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('main.url', {
        template: '<div ui-view></div>',
        url: '/url',
        abstract: true
    })
    .state('main.url.list', {
        url: '/url',
        templateUrl: 'url/views/url-list.html',
        controller: 'UrlListCtrl as vm',
    }) 
    .state('main.url.edit', {
        url: '/:id/edit',
        templateUrl: 'url/views/url-edit.html',
        controller: 'UrlEditCtrl as vm',
    });   
};
