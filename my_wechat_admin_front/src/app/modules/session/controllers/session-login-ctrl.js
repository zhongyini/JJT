/**
 * 控制器：用户登录
 */
'use strict';

function SessionLoginCtrl($state, ApiSrv, SessionSrv) {
    'ngInject';

    let vm = this;

    // 用户登录
    vm.login = function() {
        ApiSrv.exec('/login', vm.admin)
            .then(function(admin) {
                SessionSrv.saveCurrentUser(admin);
         
                SessionSrv.setAreas(admin.areas);
                if(admin.passwordOverdue){
                	$state.go('password');
                }else{
                	$state.go('main.welcome');
                }
            	
            });
    };
}

module.exports = {
    name: 'SessionLoginCtrl',
    fn: SessionLoginCtrl
};
