package cn.damei.Quartz;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;


public class SentMsgQuartz {

    @Autowired
    public BizPhoneMsgService phoneMsgService;


    public void execute() throws Exception{


        List<BizPhoneMsg> list = phoneMsgService.findPhoneMsgList();

        String url = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_HTTPS;
        String userId = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_USER_ID;
        String account = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_ACCOUNT;
        String password = PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_PASSWORD;
        if(CollectionUtils.isNotEmpty(list)) {
            for (final BizPhoneMsg phoneMsg : list) {
                try {

                    if(null == phoneMsg.getReceivePhone() || ("").equals(phoneMsg.getReceivePhone())){
                        continue;
                    }

                    Thread thread = Thread.currentThread();
                    thread.sleep(500);

                    String status = phoneMsgService.sendMessageHttps(url,userId,account,password,phoneMsg.getReceivePhone(),phoneMsg.getMsgContent());

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
