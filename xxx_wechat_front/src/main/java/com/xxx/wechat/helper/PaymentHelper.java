package com.xxx.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:payment.properties")
public class PaymentHelper {

    @Value(value = "${xxx.payment.appkey}")
    public String paymentAppkey;

    @Value(value = "${xxx.payment.url}")
    public String paymentUrl;

    @Value(value = "${xxx.payment.method}")
    public String paymentMethod;

    @Value(value = "${xxx.payment.get.url}")
    public String paymentGetUrl;

    @Value(value = "${xxx.payment.get.method}")
    public String paymentGetMethod;

}
