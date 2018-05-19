'use strict';
let _expression = {
	required: function(value) {
		return !!value;
	},
	requiredExt: function(value) {
		return !!value;
	},
	ip: function(value){
    	if(value == '' || !value){
    		return true;
    	}else{
    		return value.match(/^((\d|\d\d|[0-1]\d\d|2[0-4]\d|25[0-5])\.(\d|\d\d|[0-1]\d\d|2[0-4]\d|25[0-5])\.(\d|\d\d|[0-1]\d\d|2[0-4]\d|25[0-5])\.(\d|\d\d|[0-1]\d\d|2[0-4]\d|25[0-5]))$/);
    	}
    },
	url: /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/,
	email: function(value) {
		if(value) {
			return value.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);
		} else {
			return true;
		}
	},
	chineseAndEngMath: function(value) {
		if(value == ''|| !value ) {
			return true;
		} else {
			return value.match(/^[a-z0-9A-Z\u4e00-\u9fa5]+$/);
		}
	},
	number: /^\d+$/,
	
	minlength: function(value, scope, element, attrs, param) {
		return value.length >= param;
	},
	maxlength: function(value, scope, element, attrs, param) {
		return value.length <= param;
	},
	alphaNumber: function(value){
    	if(value == '' || !value){
    		return true;
    	}else{
    		return value.match(/^[a-zA-Z0-9]*$/);
    	}
    },
	mobilephone: /^[1-9][0-9]{10}$/,

};

let _defaultMsg = {
	required: {
		error: '请输入'
	},
	requiredExt: {
		error: '请选择'
	},
	ip: {
    	error:'请输入正确的IP格式'
    },
	chineseAndEngMath: {
		error: '请输入中文或英数字'
	},
	url: {
		error: 'URL格式不正确',
	},
	email: {
		error: '邮箱地址的格式不正确',
	},
	number: {
		error: '请输入数字'
	},
	minlength: {
		error: '最小长度不正确'
	},
	maxlength: {
		error: '最大长度不正确'
	},
	alphaNumber: {
		error: '请输入英文或者数字'
	},
	mobilephone: {
		error: '请输入正确的电话号码'
	}
};

let ValidationRules = {
	expression: _expression,
	defaultMsg: _defaultMsg
};

module.exports = {
	name: 'ValidationRules',
	fn: ValidationRules
};