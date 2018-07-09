package com.xxx.wechat.front.controller;

import com.xxx.wechat.front.authentication.LoginRequired;
import com.xxx.wechat.front.service.IWechatPaymentService;
import com.xxx.wechat.front.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("payment")
public class PaymentController extends BaseController {

    @Autowired
    private IWechatPaymentService wechatPaymentService;

    @PostMapping
    @LoginRequired(isLand = true)
    @ResponseBody
    public ResultVo payment(String openId) {
        return wechatPaymentService.payment(openId);
    }

    @GetMapping("listPayment")
    @LoginRequired(isLand = true)
    public String listPayment(Model model, String openId) {
        model.addAttribute("result", wechatPaymentService.listPayment(openId));
        return "payment/list";
    }

}
