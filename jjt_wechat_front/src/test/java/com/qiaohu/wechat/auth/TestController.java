package com.qiaohu.wechat.auth;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class TestController {

//	@Autowired
//	IActionRecommendService actionRecommendService;
	
//	@Test
//	public void test() {
//		System.out.println("hello");
//	}
	
	//查询积分
	/*@Test
	public void queryIntegrals() {
		System.out.println("queryIntegrals");
		int integrals = actionRecommendService.queryIntegrals("17955965");
		System.out.println(integrals);
	}*/
	
	//查询被推荐人的姓名
	/*@Test
	public void queryNicknameByWebId() {
		System.out.println("queryNicknameByWebId");
		List<String> nicknameList = actionRecommendService.queryNicknameByWebId("17955965");
		for (String string : nicknameList) {
			System.out.println(string);
		}
	}*/
	
	
	//获取二维码
	/*@Test
	public void getTicket() {
		System.out.println("getTicket");
		System.out.println(QrcodeType.QR_LIMIT_STR_SCENE);
		String ticket = actionRecommendService.getTicket(QrcodeType.QR_LIMIT_STR_SCENE, "", "17955965服务号一月H5活动", 0);
		System.out.println(ticket);
	}*/
	
	
	//根据web_id查询推荐人
	/*@Test
	public void queryByWebId() {
		System.out.println("queryByWebId");
		ActionRecommendExt actionRecommendExt = new ActionRecommendExt();
		actionRecommendExt.setWebId("17955965");
		actionRecommendExt.setActionId(3);
		ActionRecommend actionRecommend = actionRecommendService.queryByWebIdAndActionId(actionRecommendExt);
		System.out.println(actionRecommend);
	}*/

	//添加推荐人
	/*@Test
	public void addActionRecommend() {
		System.out.println("addActionRecommend");
		ActionRecommendExt actionRecommendExt = new ActionRecommendExt();
		actionRecommendExt.setGetIntegral(1);
		actionRecommendExt.setReasonId(3);
		actionRecommendExt.setWebId("17955965");
		actionRecommendExt.setUpdatetime(DateUtils.getNowTimestamp());
		actionRecommendExt.setTicket("ticket");
		
		int result = actionRecommendService.addActionRecommend(actionRecommendExt);
		System.out.println(result);
	}*/
		
	//更新推荐人
	/*@Test
	public void updateActionRecommend() {
		System.out.println("updateActionRecommend");
		ActionRecommend actionRecommend = new ActionRecommend();
		actionRecommend.setGetIntegral(2);
		actionRecommend.setReasonId(3);
		actionRecommend.setWebId("123456");
		actionRecommend.setTicket("ticket");
		actionRecommend.setRecommendId(4);
		actionRecommend.setActionId(3);
		int result = actionRecommendService.updateActionRecommend(actionRecommend);
		System.out.println(result);
	}*/
}
