/**
 * 控制器：管理员编辑
 */
'use strict';

function KeywordExtEditCtrl($controller, $stateParams, $state, ApiSrv, growl) {
	'ngInject';

	let vm = this;

	let sumOpt = {
		height: 150,
		disableLinkTarget: true,
		minHeight: 150, // set minimum height of editor
		maxHeight: 150,
		/*toolbar: [//文本编辑器菜单集
			['insert', ['link']]//超链接
		],*/
		toolbar: [
			['insert', ['link']],
	        ['font', ['clear']],
	        ['view', ['codeview', 'help']]
      	],
		lang: 'zh-CN'
	};
	vm.type = $stateParams.type;

	function afterGetDetail(data) {
	
		vm.model = data;
		if($('#summernote')){
			$('#summernote').summernote(sumOpt);
		}
		if(vm.type != 3) {
			
			$('#summernote').summernote('code', data.replyContent);
		}else{
			$('#replyContent').val(data.replyContent);
		}
	}
	let ctrlOpts = {
		modelName: 'keyword',
		afterGetDetail: afterGetDetail
	};
	angular.extend(this, $controller('BaseCrudCtrl', {
		vm: vm,
		ctrlOpts
	}));

	vm.getDetail1 = function() {
		ApiSrv.exec(ctrlOpts.modelName + '/detailExt?type=' + $stateParams.type, null, {
				method: 'GET'
			})
			.then(function(data) {
				if(ctrlOpts.afterGetDetail) {
					vm.model = ctrlOpts.afterGetDetail(data);
				} else {
					vm.model = data;

				}
			});
	};
	vm.getDetail1();

	// 更新
	vm.mySave = function() {
		vm.model = {};
		if(vm.type != 3) {
			var sHTML = $('#summernote').summernote('code');
			if(!sHTML) {
				return growl.error('请输入回复内容');
			}
			vm.model.replyContent = sHTML;
		}else{
			vm.model.replyContent = $('#replyContent').val();
		}

	
		vm.model.keyword = vm.type;
		ApiSrv.exec('keyword/updateExt', vm.model)
			.then(function() {
				$state.go('main.keyword.list');
			});
	};
}

module.exports = {
	name: 'KeywordExtEditCtrl',
	fn: KeywordExtEditCtrl
};