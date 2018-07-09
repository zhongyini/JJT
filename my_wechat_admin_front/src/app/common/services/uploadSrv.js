'use strict';
/* jshint ignore:start */
let _ = require('lodash');


/**
 * 上传服务
 * 基于plupload实现
 * @param {[Object]} $rootScope
 * @param {[Object]} AppConfigs [APP的配置项]
 */
function UploadSrv($rootScope, AppConfigs, MessageSrv) {
    'ngInject';


    /**
     * 创建上传组件
     * @param  {Object} opts [参考plupload的上传opts]
     */
    function createUploader(opts) {

        function _getFile() {
        	if(_.last(this.uploadedFiles)){
        		return AppConfigs.IMG_BASE_URL+  _.last(this.uploadedFiles);
        	}
            
        }
		function _getFileName() {
        	if(_.last(this.uploadedFiles)){
        		return  _.last(this.uploadedFiles).name;
        	}
            
        }
		function _getFileNameExt() {
        	if(_.last(this.uploadedFiles)){
        		return  _.last(this.uploadedFiles);
        	}
            
        }
        function _getFiles() {
            return this.uploadedFiles;
        }


        const defaultUpOpts = {
            runtimes: 'html5,flash,html4', //上传模式,依次退化
            browse_button: 'upload-btn',  // 触发文件选择对话框的按钮，为那个元素id
            url: AppConfigs.API_BASE_URL + 'admin/upload', //服务器端的上传页面地址
            file_data_name: 'file', // 指定文件上传时文件域的名称，默认为file,例如在php中你可以使用$_FILES['file']来获取上传的文件信息
            unique_names: true, // 当值为true时会为每个上传的文件生成一个唯一的文件名，并作为额外的参数post到服务器端，参数明为name,值为生成的文件名。
            // container: 'container', //用来指定Plupload所创建的html结构的父容器，默认为前面指定的browse_button的父元素。该参数的值可以是一个元素的id,也可以是DOM元素本身。
            max_file_size: '50mb', //用来限定上传文件的大小，如果文件体积超过了该值，则不能被选取。值可以为一个数字，单位为b,也可以是一个字符串，由数字和单位组成，如'200kb'
            flash_swf_url: 'images/Moxie.swf', //引入flash,相对路径
            max_retries: 1, //当发生plupload.HTTP_ERROR错误时的重试次数，为0时表示不重试
            dragdrop: true, //开启可拖曳上传
            // drop_element: 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
            multi_selection: false, //是否可以在文件浏览对话框中选择多个文件，true为可以，false为不可以。默认true，即可以选择多个文件。
            chunk_size: '50mb' //分片上传文件时，每片文件被切割成的大小，为数字时单位为字节。也可以使用一个带单位的字符串，如"200kb"。当该值为0时表示不使用分片上传功能
        };

        opts = opts || {};
        opts = _.assign(defaultUpOpts, opts);

        let uploader = new plupload.Uploader(opts);
        //在实例对象上调用init()方法进行初始化
        uploader.init();
        //绑定各种事件，并在事件监听函数中做你想做的事
        uploader.bind('FilesAdded',function(uploader,files){
        	//cfpLoadingBar.start();
        	//add file but not upload
        	$.each(uploader.files, function (i, file) {
		        if (uploader.files.length <= 1) {
		            return;
		        }
		        uploader.removeFile(file);
		    });
        	if(opts.FilesAddedNotUpload){
        		
        		opts.FilesAddedOk(files[0].name);
        	}else{
        		//成功添加了文件，会触发事件
        		uploader.start();
        	}
        	
      
        });
        uploader.bind('BeforeUpload',function(uploader,file){
            //每个文件上传前,处理相关的事情
        });
        uploader.bind('FileUploaded', function(uploader, file, info) {
        	let res = JSON.parse(info.response);
        	if (res.status == 0) {
                uploader.uploadedFiles.push(res.result);
                $rootScope.$apply();
                MessageSrv.info(res.message);
        	} else {
        		MessageSrv.error(res.message);
        	}
        });
        //进度条
        uploader.bind('UploadProgress',function(uploader,file) {
        	$('#pro').val(file.percent*0.01);
        	$('#progressbar').html(file.percent+'%');
        	
		
        });
        
        
        uploader.bind('Error',function(uploader, err, errTip) {
        	//cfpLoadingBar.complete();
        	console.log(err);
            //上传出错时,处理相关的事情
            MessageSrv.error(err.message);
        });
        
        //上传完成后刷新页面
        uploader.bind('UploadComplete',function(uploader,files) {
        	setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
				window.location.reload();//页面刷新
			},1000);
        });
        uploader.refresh();
        uploader.uploadedFiles = [];
        uploader.getFile = _getFile.bind(uploader);
        uploader.getFileName = _getFileName.bind(uploader);
        uploader.getFileNameExt = _getFileNameExt.bind(uploader);
        uploader.getFiles = _getFiles.bind(uploader);

        return uploader;
    }

    function createImageUploader(opts) {
        opts = opts || {};
        opts.filters = {
            mime_types: [
                { title: '请选择图片', extensions: 'jpg,gif,png'}
            ]
        };
        return createUploader(opts);
    }
	
	
    return {
        createUploader,
        createImageUploader
    };
}


module.exports = {
    name: 'UploadSrv',
    fn: UploadSrv
};
/* jshint ignore:end */
