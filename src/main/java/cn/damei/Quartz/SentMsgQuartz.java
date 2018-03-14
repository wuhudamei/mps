package cn.damei.Quartz;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

/**
 * HTTPS版短信发送DEMO,通过aspx接口发送短信
 * 开发环境 ：JSE1.8,httpclient4.5.2,windows 10 专业版
 * 联系方式 ：346910917@qq.com,18611729367
 * 版本：1.1
 * 最近修订：2016-12-28
 */
public class SentMsgQuartz {

    @Autowired
    public BizPhoneMsgService phoneMsgService;


    public void execute() throws Exception{

        //获取待发送的短信集合
        List<BizPhoneMsg> list = phoneMsgService.findPhoneMsgList();
        //获取短信接口信息【】
        String url = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_HTTPS;
        String userId = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_USER_ID;
        String account = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_ACCOUNT;
        String password = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_PASSWORD;
        if(CollectionUtils.isNotEmpty(list)) {
            for (final BizPhoneMsg phoneMsg : list) {
                try {
                    //如果手机号为空
                    if(null == phoneMsg.getReceivePhone() || ("").equals(phoneMsg.getReceivePhone())){
                        continue;
                    }
                    //暂停半秒后程序继续执行
                    Thread thread = Thread.currentThread();
                    thread.sleep(500);
                    //发送短信
                    String status = phoneMsgService.sendMessageHttps(url,userId,account,password,phoneMsg.getReceivePhone(),phoneMsg.getMsgContent());
                    // 发送短信完成后更新短信状态
                    if("1".equals(status)){
                        phoneMsg.setMsgStatus(PhoneMessageTypeConstant.SEND_MSG_STATUS_1);
                    }else {
                        phoneMsg.setMsgStatus(PhoneMessageTypeConstant.SEND_MSG_STATUS_2);
                    }
                    phoneMsg.setMsgSendedDatetime(new Date());
                    phoneMsgService.update(phoneMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }
}
