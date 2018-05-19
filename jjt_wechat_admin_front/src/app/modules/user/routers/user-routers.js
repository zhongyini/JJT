'use strict';
module.exports = function($stateProvider) {
	'ngInject';

	$stateProvider.state('main.user', {
			template: '<div ui-view></div>',
			url: '/user',
			abstract: true
	})
		.state('main.user.list', {
			url: '/user',
			templateUrl: 'user/views/user-list.html',
			controller: 'UserListCtrl as vm',
		})
		.state('main.user.detail', {
			url: '/:id/detail',
			templateUrl: 'user/views/user-detail.html',
			controller: 'UserDetailCtrl as vm',
		});
};