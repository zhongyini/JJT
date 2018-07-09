'use strict';
var CodeList = {
	'wxUserSex': {
		'0': '未知',
		'1': '男',
		'2': '女'
	},
	'deleteFlagUser': {
		'0': '关注中',
		'1': '取消关注'
	},
	'runStatus': {
		'0': '正在运行',
		'1': '正常完成',
		'2': '异常'
	},
	'userStatus': {
		'0': '在籍 ',
		'1': '非在籍'
	},

	'userSignStatus': {
		'0': '签到成功',
		'1': '签到失败'
	},

	'deleteFlag': {
		'0': '有效',
		'1': '已删除'
	
	},
	
	'deleteFlagTwo': {
		'2': '有效',
		'1': '已删除'
	
	},
	'Status': {
		'0': '待处理',
		'1': '已发送'
	},
	'pushStatus': {
		'0': '待处理',
		'2': '已发布'
	},
	
	'monthAge':{
		'13-15月龄': '13-15月龄',
		'16-18月龄': '16-18月龄',
		'19-21月龄': '19-21月龄',
		'22-24月龄': '22-24月龄'
	},
	
	
	
	'sectionmonthAge':{
		'A':'13-15月龄',
		'B':'16-18月龄',
		'C':'19-21月龄',
		'D':'22-24月龄'
	},
	'sendIndex':{
		'1':'1',
		'2':'2',
		'3':'3'
	},
	
	'sendFlag':{
		'1': '普通群推',
		'3': '特定用户群推'
	},
	
	'replyType': {
		'1': '文字',
		'2': '图文',
		'4': '图片'
	},
	'tempFlag': {
		'0': '临时',
		'1': '永久'
	},
	'userLevel': {
		'1': '新晋',
		'2': '进阶',
		'3': '高级',
		'4': 'VIP'
	},
	'productVersion': {
		'月龄版': '月龄版',
		'幼幼版': '幼幼版',
		'快乐版': '快乐版',
		'成长版': '成长版',
		'学习版': '学习版',
		'彩虹版': '彩虹版',
		'星空版': '星空版'
	},
	
	'sendType':{
		'1':'普通群推',
		'2':'特殊用户群推'
	},
	
	
	'massSendType':{
		'1':'普通群推',
		'2':'AB推送',
		'3':'发送次数变动群推',
		'4':'非会员群推',
		'5':'csv群推',
		'6':'特定用户群推',
		'7':'区间月龄群推',
		'8':'一般月龄群推',
		'9':'非月龄群推'
		
	},
	
	
	'goodsVersion': {
		'7月龄版': '7月龄版',
		'8月龄版': '8月龄版',
		'9月龄版': '9月龄版',
		'10月龄版': '10月龄版',
		'11月龄版': '11月龄版',
		'12月龄版': '12月龄版',
		'13月龄版': '13月龄版',
		'14月龄版': '14月龄版',
		'15月龄版': '15月龄版',
		'16月龄版': '16月龄版',
		'17月龄版': '17月龄版',
		'18月龄版': '18月龄版',
		'19月龄版': '19月龄版',
		'20月龄版': '20月龄版',
		'21月龄版': '21月龄版',
		'22月龄版': '22月龄版',
		'23月龄版': '23月龄版',
		'24月龄版': '24月龄版',
		'25月龄版': '25月龄版',
		'26月龄版': '26月龄版',
		'27月龄版': '27月龄版',
		'28月龄版': '28月龄版',
		'29月龄版': '29月龄版',
		'30月龄版': '30月龄版',
		'31月龄版': '31月龄版',
		'32月龄版': '32月龄版',
		'33月龄版': '33月龄版',
		'34月龄版': '34月龄版',
		'35月龄版': '35月龄版',
		'幼幼版': '幼幼版',
		'快乐版': '快乐版',
		'成长版': '成长版',
		'学习版': '学习版',
		'彩虹版': '彩虹版',
		'星空版': '星空版'
	},
	'pushTimes': {
		'1':'1',
		'2':'2',
		'3':'3',
		'4':'4',
		'5':'5'
	},
	'sendIndex':{
		'1':'1',
		'2':'2',
		'3':'3'
	},
	'normalMonthAge':{
		'月龄版7月龄': '月龄版7月龄',
		'月龄版8月龄': '月龄版8月龄',
		'月龄版9月龄': '月龄版9月龄',
		'月龄版10月龄': '月龄版10月龄',
		'月龄版11月龄': '月龄版11月龄',
		'月龄版12月龄': '月龄版12月龄',
		'月龄版13月龄': '月龄版13月龄',
		'月龄版14月龄': '月龄版14月龄',
		'月龄版15月龄': '月龄版15月龄',
		'月龄版16月龄': '月龄版16月龄',
		'月龄版17月龄': '月龄版17月龄',
		'月龄版18月龄': '月龄版18月龄',
		'月龄版19月龄': '月龄版19月龄',
		'月龄版20月龄': '月龄版20月龄',
		'月龄版21月龄': '月龄版21月龄',
		'月龄版22月龄': '月龄版22月龄',
		'月龄版23月龄': '月龄版23月龄',
		'月龄版24月龄': '月龄版24月龄',
		'月龄版25月龄': '月龄版25月龄',
		'月龄版26月龄': '月龄版26月龄',
		'月龄版27月龄': '月龄版27月龄',
		'月龄版28月龄': '月龄版28月龄',
		'月龄版29月龄': '月龄版29月龄',
		'月龄版30月龄': '月龄版30月龄',
		'月龄版31月龄': '月龄版31月龄',
		'月龄版32月龄': '月龄版32月龄',
		'月龄版33月龄': '月龄版33月龄',
		'月龄版34月龄': '月龄版34月龄',
		'月龄版35月龄': '月龄版35月龄'
	},
	'normalSection':{
		'0':'7~12月龄',
		'1':'25~35月龄'
	},
	'noMonthAge': {
		'幼幼版': '幼幼版',
		'快乐版': '快乐版',
		'成长版': '成长版',
		'学习版': '学习版',
		'彩虹版': '彩虹版',
		'星空版': '星空版'
	},
	'months':{
		'1':'1月',
		'2':'2月',
		'3':'3月',
		'4':'4月',
		'5':'5月',
		'6':'6月',
		'7':'7月',
		'8':'8月',
		'9':'9月',
		'10':'10月',
		'11':'11月',
		'12':'12月',
	},
	'editStatus':{
		'0':'待编辑',
		'1':'已编辑'
	},
	'tradeType': {
		'0': '主题商品',
		'1': '周边商品',
		'2': '单品'
	},
	'activityType': {
		'0': '是',
		'1': '否'
	},
	'keyType': {
		'1': '关键字',
		'2': '常见问题'
	},
	'previewType': {
		'1': '线下活动',
		'2': '群推'
	},
	'permission': {
		'authority.adminManager.list': '管理员管理',
		'authority.adminManager.update': '管理员编辑',
		'authority.adminManager.create': '管理员新增',
		'authority.adminManager.delete': '管理员删除',
		'authority.api.create':'授权新增',
		'authority.api.delete':'授权删除',
		'authority.api.list':'授权管理',
		'authority.api.update':'授权编辑',
		'authority.role.create': '角色新增',
		'authority.role.delete': '角色删除',
		'authority.role.list': '角色管理',
		'authority.role.update': '角色编辑',
		'authority.preview.create':'预览新增',
		'authority.preview.delete':'预览删除',
		'authority.preview.list':'预览管理',
		'authority.preview.update':'预览更新',
		'wx.offactivity.create': '线下活动新增',
		'wx.offactivity.delete': '线下活动删除',
		'wx.offactivity.list': '线下活动管理',
		'wx.offactivity.update': '线下活动编辑',
		'wx.keyword.create': '关键字新增',
		'wx.keyword.delete': '关键字删除',
		'wx.keyword.list': '关键字管理',
		'wx.keyword.update': '关键字编辑',
		'wx.actionKeyword.create': '活动关键字新增',
		'wx.actionKeyword.delete': '活动关键字删除',
		'wx.actionKeyword.list': '活动关键字管理',
		'wx.actionKeyword.update': '活动关键字编辑',
		'wx.faq.create': '常见问题新增',
		'wx.faq.delete': '常见问题删除',
		'wx.faq.list': '常见问题管理',
		'wx.faq.update': '常见问题编辑',
		'wx.notice.create': '积分到账新增',
		'wx.messageTemplate.create': '活动参与成功通知',
		'wx.news.create': '新闻新增',
		'wx.news.delete': '新闻删除',
		'wx.news.list': '新闻管理',
		'wx.news.update': '新闻编辑',
		'wx.push.create': '群推添加',
		'wx.push.special': '特定用户群推添加',
		'wx.push.delete': '群推删除',
		'wx.push.list': '群推管理',
		'wx.push.update': '群推编辑',
		'wx.groupsetting.create': 'AB组群推新增',
		'wx.groupsetting.delete': 'AB组群推删除',
		'wx.groupsetting.list': 'AB组群推管理',
		'wx.deliverTime.list': '发货次数群推管理',
		'wx.deliverTime.create': '发货次数群推新增',
		'wx.deliverTime.delete': '发货次数群推删除',
		'wx.deliverTime.update': '发货次数群推编辑',
		'wx.normalMonthAge.list': '一般月龄群推管理',
		'wx.normalMonthAge.create': '一般月龄群推新增',
		'wx.normalMonthAge.delete': '一般月龄群推删除',
		'wx.normalMonthAge.update': '一般月龄群推编辑',
		'wx.noMonthAge.list': '非月龄群推管理',
		'wx.noMonthAge.create': '非月龄群推新增',
		'wx.noMonthAge.delete': '非月龄群推删除',
		'wx.noMonthAge.update': '非月龄群推编辑',
		'wx.sectionMonthAge.list':'区间月龄群推管理',
		'wx.sectionMonthAge.create':'区间月龄群推新增',
		'wx.sectionMonthAge.update':'区间月龄群推编辑',
		'wx.sectionMonthAge.delete':'区间月龄群推删除',
		'wx.scene.create': '场景新增',
		'wx.scene.delete': '场景删除',
		'wx.scene.list': '场景管理',
		'wx.scene.update': '场景编辑',
		'wx.scene.all':'全部二维码',
		'wx.trade.create': '商品新增',
		'wx.trade.delete': '商品删除',
		'wx.trade.list': '商品管理',
		'wx.trade.update': '商品编辑',
		'wx.subjectgoods.create': '主题商品推送新增',
		'wx.subjectgoods.delete': '主题商品推送删除',
		'wx.subjectgoods.list': '主题商品推送管理',
		'wx.subjectgoods.update': '主题商品推送编辑',
		'wx.subjectgoods.detail': '主题商品推送详情',
		'wx.industrymodel.create': '行业模板新增',
		'wx.industrymodel.delete': '行业模板删除',
		'wx.industrymodel.list': '行业模板管理',
		'wx.industrymodel.update': '行业模板编辑',
		'wx.url.list': 'URL设定',
		'wx.url.update': 'URL编辑',
		'wx.csv.upload': '会员到期CSV上传',
		'wx.csv.phupload': '群推CSV上传',
		'wx.csv.modelupload':'服务变更CSV上传',
		'wx.csv.industryupload':'行业模板CSV上传',
		'report.accessTimes.list':'接入次数统计',
		'report.useTimes.list':'客服使用统计',
		'report.userAppend.list':'用户数据统计', //新增用户统计 add by lm 20180324
		'report.statisticalData.list':'图文分析数据统计', //新增用户统计 add by lm 20180324
		'search':'信息查询',
		'search.pushret.list': '推送结果一览',
		'search.pushret.detail': '推送结果详细',
		'search.batch.list':'批处理一览',
		'search.user.list': '微信用户一览',
		'search.user.detail':'微信用户详细',
		'search.speciallist.list': '特定微信用户一览',
		'search.industrymodel.detail':'行业模板详细',
		'search.userSign.list': '微信用户签到一览',
		'search.userActionRecord.list': '微信用户操作记录一览',
		'authority':'权限管理',
		'report':'报表统计',
		'wx':'微信管理',
		'wx.banner.list':'Banner管理',
		'wx.banner.create':'Banner管理新增',
		'wx.banner.update':'Banner管理编辑',
		'wx.banner.delete':'Banner管理删除'
	}
   
};

module.exports = {
	name: 'CodeList',
	fn: CodeList
};