<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>九间堂新中医</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="${ctx}/static/css/xxx_common.css">
    <style type="text/css">
        .head {
            margin-top: 1rem;
            margin-bottom: 1rem;
        }

        .red_recode_title {
            font-size: 1.2rem;
            text-align: center;
        }

        .content_div_white {
            color: #BD9146;
            background-color: white;
            border: none;
            text-align: center;
            font-size: 1rem;
            margin-left: 5%;
            width: 90%;
            border-radius: 0.4rem;
            overflow: hidden;
        }

        .recode_line_hr {
            width: 86%;
            margin-left: 7%;
            border: 1px;
            border-color: #aaaaaa;
            border-style: dashed;
        }

        .recode_title_margin {
            padding-top: 1rem;
            padding-bottom: 1rem;
        }

        .red_recode_title_table {
            font-size: 0.8rem;
            color: #666666;

        }

        .red_recode_title_td1 {
            text-align: left;
            width: 32rem;
            padding-left: 1rem;
        }

        .red_recode_title_td2 {
            text-align: right;
            min-width: 7rem;
            padding-right: 1rem;
        }

        .red_recode_input_button {
            color: white;
            border: none;
            text-align: center;
            font-size: 1rem;
            height: 2rem;
            border-radius: 0.4rem;
            width: 4rem;
        }

        .red_content_list {
            height: 3rem;
            font-size: 0.9rem;
            text-align: left;
            color: #aaaaaa;
        }

        .red_recode_unreceive {
            background-color: #BD9146;
        }

        .red_recode_receive {
            background-color: #cccccc;
        }

        .red_content_list_td1 {
            width: 30rem;
        }

        .red_content_list_td2 {
            min-width: 3rem;
            margin-left: 1rem;
            color: #777777;
        }

        .red_content_list_table,
        .red_content_list,
        .red_content_list td {
            border-bottom: 1px solid #eeeeee;
        }

        .red_content_list_table {
            border-collapse: collapse;
            margin-left: 1rem;
            margin-right: 1rem;
            margin-bottom: -1px;
            overflow: hidden;
        }
    </style>

    <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/js/alert.js"></script>

</head>
<body>
<!--head-->
<div class="head">
    <p class="red_recode_title"><strong>红包提现记录</strong></p>
</div>

<!--content-->
<div class="content">
    <div class="content_div_white recode_title_margin">
        <table class="red_recode_title_table">
            <tr>
                <td class="red_recode_title_td1">${result.data.total}笔红包收入</td>
                <td class="red_recode_title_td2">
                    收入 ￥ <fmt:formatNumber pattern="#,##0.00#" value="${result.data.totalAmount / 100}"/>元
                </td>
            </tr>
        </table>
    </div>
    <hr class="recode_line_hr"/>
    <div class="content_div_white">
        <table class="red_content_list_table">

            <c:forEach items="${result.data.data}" var="payment">
                <tr class="red_content_list">
                    <td class="red_content_list_td1">
                        <fmt:formatDate value="${payment.updateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td class="red_content_list_td2">
                        <fmt:formatNumber pattern="#,##0.00#" value="${payment.amount / 100}"/>元
                    </td>
                    <td class="red_content_list_td3">
                        <c:if test="${payment.timeout == 0}">
                            <input class="red_recode_input_button red_recode_unreceive"
                                   onclick="getMoney('${payment.redpackUrl}')" value="领取">
                        </c:if>
                        <c:if test="${payment.timeout != 0}">
                            <input class="red_recode_input_button red_recode_receive" value="已领取">
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

<script>
    function getMoney(url) {
        if (url) {
            window.location.href = url;
        } else {
        	showModal({
                title:"九间堂新中医",
                content:"领取失败",
                showCancel:false,
                confirmText:'确定',
                confirmColor:'#ffffff',
                success:function (res) {
                   
                }
            });
        }
    }
</script>

</body>
</html>