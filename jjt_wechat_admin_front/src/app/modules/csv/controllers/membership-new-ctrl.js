/**
 * 控制器：会员到期CSV文件上传
 */
'use strict';

function MembershipNewCtrl($controller, UploadSrv, AppConfigs,MessageSrv) {
	'ngInject';

	let vm = this;
	let uploader;

	let ctrlOpts = {
		modelName: 'csv'
	};
	angular.extend(this, $controller('BaseCrudCtrl', {
		vm: vm,
		ctrlOpts
	}));

	function FilesAddedOk(name) {
		$('#fileName').html(name);
	}
	let opts = {
			browse_button: 'browse_button',
			url: AppConfigs.API_BASE_URL + 'csv/upload',
			max_file_size: '50mb',
			dragdrop: false,
			filters: {
				mime_types: [ //只允许上传图片和zip文件
					{
						title: "请选择csv文件",
						extensions: "csv"
					}
				],
				max_file_size: '50mb', //最大只能上传400kb的文件
				prevent_duplicates: true //不允许选取重复文件
			},
			FilesAddedNotUpload: true,
			FilesAddedOk: FilesAddedOk
		}
		// 上传组件
	uploader = UploadSrv.createUploader(opts);

	vm.uploader = uploader;
	
	//鼠标移动至选择文件，浮动显示提示框
	$('#browse_button').
            tooltip({
            trigger:'hover',
            html:true,
            title:'选择文件格式例如：member20170720-1.csv'}).popover({
                                               trigger:'click',
                                               placement:'bottom',
                                               content:''});
	vm.mySave = function() {
		
		var fileName =document.getElementById('fileName').innerText;
		if(fileName==''||fileName==null){
			MessageSrv.error('请选择您要上传的CSV文件！');
			return false;
		}
		uploader.setOption('multipart_params', {
			'url': vm.model.url,
			'firstData': vm.model.firstData,
			'remarkData': vm.model.remarkData,
		});
		uploader.start();
		
		
	};
}

module.exports = {
	name: 'MembershipNewCtrl',
	fn: MembershipNewCtrl
};