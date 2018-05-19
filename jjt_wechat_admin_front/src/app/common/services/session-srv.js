/**
 * Session相关服务
 */
'use strict';

function SessionSrv(localStorageService) {
	'ngInject';

	let KEY_CURRENT_USER = 'CURRENT_USER';

	let _currentUser;

	let KEY_ARESA = 'AREAS';

	let _areas;

	/**
	 * 保存当前用户
	 * @param  Object user 当前用户
	 */
	let _saveCurrentUser = function(user) {
		if(user) {
			_currentUser = user;
			localStorageService.set(KEY_CURRENT_USER, user);
		}
	};

	/**
	 * 清除当前用户
	 */
	let _clearCurrentUser = function() {
		localStorageService.remove(KEY_CURRENT_USER);
		localStorageService.remove(KEY_ARESA);
		_currentUser = null;
		_areas = null;
	};

	/**
	 * 取得当前用户
	 * @result  Object user 当前用户
	 */
	let _getCurrentUser = function() {
		if(!_currentUser) {
			_currentUser = localStorageService.get(KEY_CURRENT_USER);
		}
		return _currentUser;
	};
	/**
	 * 取得当前用户Id
	 * @result  Object user 当前用户Id
	 */
	let _getCurrentUserId = function() {
		if(!_currentUser) {
			_currentUser = localStorageService.get(KEY_CURRENT_USER);
		}
		if(_currentUser && _currentUser.admin) {
			return _currentUser.admin.adminId;
		}
		return '';
	};
	/**
	 * 取得当前用户角色ID
	 * @result  Object user 当前用户Id
	 */
	let _getCurrentUserRoleId = function() {
		if(!_currentUser) {
			_currentUser = localStorageService.get(KEY_CURRENT_USER);
		}
		if(_currentUser && _currentUser.admin) {
			return _currentUser.admin.roleId;
		}
		return _currentUser;
	};
	let _setAreas = function(areas) {
		if(areas) {
			_areas = areas;
			localStorageService.set(KEY_ARESA, areas);
		}
	};

	let _getAreas = function() {

		if(!_areas) {
			_areas = localStorageService.get(KEY_ARESA);
		}

		return _areas;
	};
	return {
		saveCurrentUser: _saveCurrentUser,
		clearCurrentUser: _clearCurrentUser,
		getCurrentUser: _getCurrentUser,
		setAreas: _setAreas,
		getAreas: _getAreas,
		getCurrentUserId: _getCurrentUserId,
		getCurrentUserRoleId: _getCurrentUserRoleId
	};
}

module.exports = {
	name: 'SessionSrv',
	fn: SessionSrv
};