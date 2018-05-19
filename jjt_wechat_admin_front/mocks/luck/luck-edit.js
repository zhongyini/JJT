/**
 * mock for 编辑抽奖
 * @type {Object}
 *
 * params:{
 *		giftId: "礼品ID ",
 * 	 	odds: "中奖概率",
 *  	dailyWinningTimes: "每日中奖次数",
 *  	eventStartDate: "活动开始日期",
 *  	eventStartTime: "活动开始时间",
 *  	eventEndDate: "活动结束日期",
 *  	eventEndTime: "活动结束时间",
 *  	exchangeStartDate: "兑奖开始日期",
 *  	exchangeStartTime: "兑奖开始时间",
 *  	exchangeEndDate: "兑奖结束日期",
 *  	exchangeEndTime: "兑奖结束时间",
 *  	dailyLuckyDrawTimes: "每日抽奖赠送次数",
 *  	luckyDrawCost: "抽奖成本",
 *  	loseDescription: "没中奖提示",
 *  	winDescription: "中奖提示"}
 */
module.exports = {
    '/api/luck/update': {
        POST: {
            data: {
                status: 0,
                message: '新增抽奖成功！'
            }
        }
    }
};
