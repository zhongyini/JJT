/**
 * 控制器：用户修改密码
 */
'use strict';

function SessionPasswordCtrl($state, ApiSrv, SessionSrv,$scope,MessageSrv) {
    'ngInject';

    let vm = this;
	vm.admin =  SessionSrv.getCurrentUser().admin;
	
	//**********************密码强度**********************
	// 初始化数据
    vm.admin.password = null;
    $scope.confirmPswd = null;
	 $scope.flag =0;
    // 新密码合法条件
    $scope.newPswdValidOne = null; // 条件1：8~16个字符
    $scope.newPswdValidTwo = null; // 条件2：只能包含数字，字母和"_" "."（即不能含有非法特殊字符）
    $scope.newPswdValidThree = null; // 条件3：数字，字母和"_" "." 至少包含两种
    // 新密码强度等级 0(无等级,密码不合法) 1（弱） 2（中） 3（强）
    $scope.newPswdRank = 0;

    // 新密码强度等级文字 无等级,密码不合法(0) 弱(1) 中(2) 强(3)
    $scope.newPswdRankText = "";

    // 新密码输入焦点
    $scope.bNewPswdFocus = false;
    // 确认密码输入焦点
    $scope.bConfirmPswdFocus = false;

    //新密码是否合法
    $scope.newPswdValid = null;
    // 确认密码是否合法
    $scope.confirmPswdValid = null;

    // 新密码长度是否合法：8~16 
    $scope.newPswdLenValid = null;
    // 是否包含指定特殊字符
    $scope.newPswdHasSChar = null;
    // 是否包含字母
    $scope.newPswdHasLiter = null;
    // 是否包含数字
    $scope.newPswdHasNumber = null;

    // 新密码输入框获取焦点
    $scope.newPswdFocus = function() {
        $scope.bNewPswdFocus = true; // 标识获取焦点，显示等级和要求信息
        //resizeMainContent($scope.bNewPswdFocus);
    }

    // 新密码输入框内容发生改变时
    $scope.newPswdChange = function() {
        if (!vm.admin.password) {//如果为空时
            $scope.newPswdValidOne = null; // 条件1：8~16个字符
            $scope.newPswdValidTwo = null; // 条件2：只能包含数字，字母和"_" "."（即不能含有非法特殊字符）
            $scope.newPswdValidThree = null; // 条件3：数字，字母和"_" "." 至少包含两种
        } 
        else {//不为空时
            var len = vm.admin.password.length; // 获取字符串长度
            var matchResult = JSON.parse(calcCharCount(vm.admin.password)); // 查找 '-' '.' 特殊字符的数量
            // 条件1 判断
            $scope.newPswdValidOne = (len >= 5 && len <= 16);
            if($scope.newPswdValidOne){
            		 $scope.flag =1;
            }else{
            	 $scope.flag =2;
            }
            		
            
            // 条件2 判断
            $scope.newPswdValidTwo = !isIncludeSChar(vm.admin.password);

            // 包含 '-' '.' 特殊字符
            $scope.newPswdHasSChar = $scope.newPswdValidTwo && (matchResult.lineCounts > 0 || matchResult.dotCounts > 0);
            // 包含字母
            $scope.newPswdHasLiter = isIncludeLiter(vm.admin.password);
            // 包含数字
            $scope.newPswdHasNumber = isIncludeNum(vm.admin.password);

            // 条件3 判断
            $scope.newPswdValidThree = ($scope.newPswdHasNumber && $scope.newPswdHasLiter) || // 数字和字母
            ($scope.newPswdHasNumber && $scope.newPswdHasSChar) || // 数字和特殊字符
            ($scope.newPswdHasLiter && $scope.pswdValidChars) || // 字母和特殊字符
            ($scope.newPswdHasLiter && $scope.newPswdHasNumber && $scope.pswdValidChars); // 数字、字母和特殊字符
            // 新密码是否合法
            $scope.newPswdValid = $scope.newPswdValidOne && $scope.newPswdValidTwo && $scope.newPswdValidThree;
			//弱的合法性
			$scope.newPswdValid2 =$scope.newPswdValidOne && $scope.newPswdValidTwo;
            // 密码等级判断
            // 3.口令中包含数字、字母和多个特殊字符时，密码强度强
            if ($scope.newPswdValid && $scope.newPswdHasNumber && $scope.newPswdHasLiter && (matchResult.lineCounts > 0 && matchResult.dotCounts > 0)) {
                $scope.newPswdRank = 3;
                $scope.newPswdRankText = "强";
            }
            // 2.口令中包含数字、字母和任一特殊字符时，密码强度中；
            else if ($scope.newPswdValid && $scope.newPswdHasNumber && $scope.newPswdHasLiter && (matchResult.lineCounts > 0 || matchResult.dotCounts > 0)) {
                $scope.newPswdRank = 2;
                $scope.newPswdRankText = "中";
            }
            // 1.口令中仅包含数字、字母时,密码强度弱;
            else if ($scope.newPswdHasNumber&&$scope.newPswdValid2||$scope.newPswdHasLiter&&$scope.newPswdValid2 ||$scope.newPswdValid && $scope.newPswdHasNumber && $scope.newPswdHasLiter) {
                $scope.newPswdRank = 1;
                $scope.newPswdRankText = "弱";
            }
            //	密码不合法
            else {
                $scope.newPswdRank = 0;
                $scope.newPswdRankText = "";
            }

            $scope.confirmPswdValid = $scope.confirmPswd == vm.admin.password;
        }
    }

    // 新密码输入框失去焦点
    $scope.newPswdBlur = function() {
        $scope.bNewPswdFocus = false;
        //resizeMainContent($scope.bNewPswdFocus);
    }

    // 确认密码框获取输入焦点
    $scope.confirmPswdFocus = function() {
        $scope.bConfirmPswdFocus = true;
    }

    // 确认密码框输入内容发生改变
    $scope.confirmPswdChange = function() {
        // 输入内容为空，不显示提示信息
        if (!$scope.confirmPswd) {
            $scope.confirmPswdValid = false;
        } else {
            $scope.confirmPswdValid = $scope.confirmPswd == vm.admin.password;
        }
    }

    // 确认密码框失去输入焦点
    $scope.confirmPswdBlur = function() {
        $scope.bConfirmPswdFocus = false;
    }
    //**************************************************************
	
    // 用户修改密码
    vm.updatePassword = function() {
    	
    	if(vm.admin.password){
    		if(vm.admin.password.length<5||vm.admin.password.length>16){
    			return MessageSrv.error('新旧密码长度不能小于5位大于16位！');
    		}
    		
    	}
    	if(vm.admin.password===vm.admin.oldPassword){
    		return MessageSrv.error('新旧密码不能相同，请重新输入新密码！');
    	}
    	if(vm.admin.password!==$scope.confirmPswd){
    		return MessageSrv.error('确认密码和新密码不一致，请重新输入确认密码！');
    	}
        ApiSrv.exec('/adminManager/updateOldDatePassword', vm.admin)
            .then(function(admin) {
            	$state.go('login');
            });
    };
}
/*=============================================================================
    函 数 名:isIncludeSChar
    功    能:检测是否包含字特殊字符：去除 "-" "."
    参    数:校验字符串
    注    意: 
    	所有特殊字符：[`~!@#$^&*%()_+=|{}':;',\\-\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]
    返 回 值:true 含有特殊字符
    -------------------------------------------------------------------------------
    修改纪录:
    日      期  版本    修改人  修改内容
2017/09/29  1.0     刘波   创建
=============================================================================*/
function isIncludeSChar(strData)
{
	if(strData == ""){
		return false;
	}
	// 全部特殊字符
 	var reg = new RegExp("[`~!@#$^&*%()_+=|{}':;',\\[\\]<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]") 
 	return reg.test(strData);
}

/*=============================================================================
    函 数 名:calcCharCount
    功    能:统计字符串strData中特殊字符  "-" "." 的数量
    参    数:strData 校验字符串
    注    意: 
    返 回 值:统计结果对象
    -------------------------------------------------------------------------------
    修改纪录:
    日      期  版本    修改人  修改内容
 2017/09/29  1.0     刘波    创建
=============================================================================*/
function calcCharCount(strData) {
	var matchResult = {
		lineCounts: 0, //'-'个数
		dotCounts: 0 // '.'个数
	};

	if(!strData){
		return JSON.stringify(matchResult);
	}

	var lineChar = "-", dotChar = "\\."; // 要统计的字符
	var lineRegex = new RegExp(lineChar, 'g'), dotRegex = new RegExp(dotChar, 'g'); // 使用g表示整个字符串都要匹配
	
	var lineResult = strData.match(lineRegex);
	matchResult.lineCounts = !lineResult ? 0 : lineResult.length;

	var dotResult = strData.match(dotRegex);
	matchResult.dotCounts = !dotResult ? 0 : dotResult.length;

	return JSON.stringify(matchResult);
}

/*=============================================================================
    函 数 名:isIncludeLiter
    功    能:检测是否包含字母
    参    数:校验字符串
    注    意: 
    返 回 值:true 含有字母 
    -------------------------------------------------------------------------------
    修改纪录:
    日      期  版本    修改人  修改内容
 2017/09/29  1.0     刘波    创建
=============================================================================*/
function isIncludeLiter(strData)
{
	if(!strData){
		return false;
	}
 	var reg = /[a-z]/i;
 	if (!reg.test(strData))
 	{
		return false;
 	}
 	return true;
}

/*=============================================================================
    函 数 名:isIncludeNum
    功    能:检测是否包含数字
    参    数:校验字符串
    注    意: 
    返 回 值:true 含有数字
    -------------------------------------------------------------------------------
    修改纪录:
    日      期  版本    修改人  修改内容
 2017/09/29  1.0     刘波  创建
=============================================================================*/
function isIncludeNum(strData)
{
	if(!strData){
		return false;
	}
 	var reg = /[0-9]/;
 	if (!reg.test(strData))
 	{
		return false;
 	}
 	return true;
}

module.exports = {
    name: 'SessionPasswordCtrl',
    fn: SessionPasswordCtrl
};
