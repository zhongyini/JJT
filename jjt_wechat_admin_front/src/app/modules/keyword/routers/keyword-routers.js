'use strict';
module.exports = function($stateProvider) {
    'ngInject';

    $stateProvider.state('main.keyword', {
        template: '<div ui-view></div>',
        url: '/keyword',
        abstract: true
    })
    .state('main.keyword.list', {
        url: '/',
        templateUrl: 'keyword/views/keyword-list.html',
        controller: 'KeywordListCtrl as vm',
    })    
    .state('main.keyword.new', {
    	url: '/new',
        templateUrl: 'keyword/views/keyword-new.html',
        controller: 'KeywordNewCtrl as vm',
    })
    .state('main.keyword.detail', {
        url: '/:id/detail',
        templateUrl: 'keyword/views/keyword-detail.html',
        controller: 'KeywordDetailCtrl as vm',
    })
    .state('main.keyword.edit', {
        url: '/:id/edit',
        templateUrl: 'keyword/views/keyword-edit.html',
        controller: 'KeywordEditCtrl as vm',
    })
    .state('main.keyword.update', {
        url: '/:type/update',
        templateUrl: 'keyword/views/keywordExt-edit.html',
        controller: 'KeywordExtEditCtrl as vm',
    })
    .state('main.keyword.delete', {
        url: '/:id/delete',
        templateUrl: 'keyword/views/keyword-detail.html',
        controller: 'KeywordDeleteCtrl as vm'
    });    
};
