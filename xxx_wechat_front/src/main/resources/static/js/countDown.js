self.setInterval("countDown()", 1000);
self.setInterval("countDownHour()", 1000);

//天 小时  分钟 秒
function countDown(){
    var leftTime = (new Date(2018, 7, 1, 0, 0, 0)) - (new Date());
    var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数
    var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时
    var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟
    var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数
    days = checkTime(days);
    hours = checkTime(hours);
    minutes = checkTime(minutes);
    seconds = checkTime(seconds);
    var countDown = days + "天" + hours + ":"+minutes+":"+seconds;
    if (document.getElementById("timer")) {
    	document.getElementById("timer").innerHTML = "倒计时"+countDown;
    }
}

//小时  分钟  秒
function countDownHour(){
    var leftTime = (new Date(2018, 7, 1, 0, 0, 0)) - (new Date());
    var hours = parseInt(leftTime / 1000 / 60 / 60, 10); //计算剩余的小时
    var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟
    var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数
    hours = checkTime(hours);
    minutes = checkTime(minutes);
    seconds = checkTime(seconds);
    var countDown = hours + ":"+minutes+":"+seconds;
    if (document.getElementById("timer2")) {
    	document.getElementById("timer2").innerHTML = countDown;
    }
}
function checkTime(i){
    if(i<10)
    {
        i = "0" + i;
    }
    return i;
}