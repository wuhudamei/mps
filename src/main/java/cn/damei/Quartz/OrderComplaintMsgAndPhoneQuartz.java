package cn.damei.Quartz;

import cn.damei.dao.modules.OrderComplaintMsgAndPhoneQuartzDao;
import cn.damei.common.constantUtils.appMegTypeConstant.AppMsgTypeConstant;
import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.appMsgUtil.AppMsgEntity;
import cn.damei.common.utils.appMsgUtil.AppMsgUtils;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;

import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/7/31.
 * 定时抓取未完成的投诉数据,根据超时时限判断 对处理人员(项目经理,质检员)发送app通知和短信提醒
 */

@Service
public class OrderComplaintMsgAndPhoneQuartz {


    private static final String appMsgType = AppMsgTypeConstant.ORDER_COMPLAINT_URGE_TYPE_1000;
    private static final String phoneMessageType = PhoneMessageTypeConstant.ORDER_COMPLAINT_URGE_TYPE_1000;

    private static final String phoneMessageStatus = PhoneMessageTypeConstant.SEND_MSG_STATUS_0;


    private String customerName = "";
    private String customerInfo = "";
    private String problemName = "";


    private static final String appMsgTitle = "工程投诉超时提醒";

    //短信

    @Autowired
    private PhoneMessageDao phoneMessageDao;


    //消息
    @Autowired
    private AppMsgUtils appMsgUtils;


    @Autowired
    private OrderComplaintMsgAndPhoneQuartzDao dao;

    /**
     * 订单投诉超时 发送短信和站内消息
     */
    public void execute() {
        Date currentDate = new Date();
        List<AppMsgEntity> allMsgEntityList = new ArrayList<>(48);
        List<PhoneMessageEntity> allPhoneMessageList = new ArrayList<>(48);


//拿到所有(经理和质检)没有处理的投诉且投诉项有配置超时时间的数据集合
        List<Map<String, Object>> allDataInfo = dao.getAllDataForQuartz();
        int resultSize = allDataInfo.size();
        if (resultSize > 0) {


            for (int i = 0; i < resultSize; i++) {
                Map<String,Object> dataMap = allDataInfo.get(i);
                String responseTime = allDataInfo.get(i).get("responseTime").toString();


                //校验数据类型
                if (isNumeric(responseTime)) {

                    //当前时间
                    Date problemCreateDate = (Date) dataMap.get("createDate");
                    //问题的截止时间
                    Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());
                    //如果超时
                    if (currentDate.getTime() > problemDelayDate.getTime()) {

                        //判断是否插入


                        //发送短信和消息
                        customerInfo = dataMap.get("customerInfo").toString();
                        customerName = dataMap.get("customerName").toString();
                        problemName = dataMap.get("itemName").toString();

                        PhoneMessageEntity entity = new PhoneMessageEntity();

                        entity.setReceiveEmployeeId((Integer) dataMap.get("dealEmployeeId"));

                        entity.setReceivePhone((String) dataMap.get("dealEmployeePhone"));
                        entity.setMessageContent(getContent());
                        entity.setMessageGenerateTime(currentDate);
                        entity.setStatus(phoneMessageStatus);

                        entity.setRelatedBusinessId((Integer) dataMap.get("handleId"));

                        entity.setRelatedBusinessType(phoneMessageType);
                        if (1  <=phoneMessageDao.checkIsExistByTypeAndBusinessId(entity)) {


                        } else {

                            allPhoneMessageList.add(entity);
                        }


                        AppMsgEntity appMsgEntity = new AppMsgEntity();

                        appMsgEntity.setMsgTitle(appMsgTitle);

                        appMsgEntity.setMsgDateTime(currentDate);
                        appMsgEntity.setMsgContent(getContent());
                        appMsgEntity.setMsgType("1");
                        appMsgEntity.setRelatedBusinessType(appMsgType);
                        appMsgEntity.setRelatedBusinessId(dataMap.get("handleId").toString());

                        appMsgEntity.setEmployeeId((Integer) dataMap.get("dealEmployeeId"));


                        if (1  <= appMsgUtils.checkIsExistByTypeAndId(appMsgEntity.getRelatedBusinessType(), appMsgEntity.getRelatedBusinessId())) {


                        } else {
                            allMsgEntityList.add(appMsgEntity);

                        }

                    }


                }


            }

            //for Each 完毕  批量插入

            if(allPhoneMessageList.size()>0){
                phoneMessageDao.batchSavePhoneInfo(allPhoneMessageList);

            }
            if(allMsgEntityList.size()>0){
                appMsgUtils.batchSaveAppMsgContent(allMsgEntityList);

            }



        }


    }


    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {

            return false;
        }


    }


    public String getContent() {


        String content = "工程投诉 : 客户" + customerName + "，地址" + customerInfo + "的工地存在“" + problemName + "”的工程问题，已超过标准执行时限，请您及时到大美装饰管理平台工程APP的工程投诉中查看投诉问题内容，并尽快处理答复，以免延期造成罚款。";

        return content;

    }
































    /**
     * 订单投诉超时 发送短信和站内消息
     */
    public String execute2() {
        String result ="";
        Date currentDate = new Date();
        List<AppMsgEntity> allMsgEntityList = new ArrayList<>(48);
        List<PhoneMessageEntity> allPhoneMessageList = new ArrayList<>(48);


//拿到所有(经理和质检)没有处理的投诉且投诉项有配置超时时间的数据集合
        List<Map<String, Object>> allDataInfo = dao.getAllDataForQuartz();
        int resultSize = allDataInfo.size();
        if (resultSize > 0) {


            for (int i = 0; i < resultSize; i++) {

                Map<String,Object> dataMap = allDataInfo.get(i);

                String responseTime = dataMap.get("responseTime").toString();


                //校验数据类型
                if (isNumeric(responseTime)) {

                    //当前时间
                    Date problemCreateDate = (Date) dataMap.get("createDate");
                    //问题的截止时间
                    Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());
                    //如果超时
                    if (currentDate.getTime() > problemDelayDate.getTime()) {

                        //判断是否插入


                        //发送短信和消息
                        customerInfo = dataMap.get("customerInfo").toString();
                        customerName = dataMap.get("customerName").toString();
                        problemName = dataMap.get("itemName").toString();

                        PhoneMessageEntity entity = new PhoneMessageEntity();

                        entity.setReceiveEmployeeId((Integer) dataMap.get("dealEmployeeId"));

                        entity.setReceivePhone((String) dataMap.get("dealEmployeePhone"));
                        entity.setMessageContent(getContent());
                        entity.setMessageGenerateTime(currentDate);
                        entity.setStatus(phoneMessageStatus);

                        entity.setRelatedBusinessId((Integer) dataMap.get("handleId"));

                        entity.setRelatedBusinessType(phoneMessageType);
                        if (1 <= phoneMessageDao.checkIsExistByTypeAndBusinessId(entity)) {


                        } else {

                            allPhoneMessageList.add(entity);
                        }


                        AppMsgEntity appMsgEntity = new AppMsgEntity();

                        appMsgEntity.setMsgTitle(appMsgTitle);

                        appMsgEntity.setMsgDateTime(currentDate);
                        appMsgEntity.setMsgContent(getContent());
                        appMsgEntity.setMsgType("1");
                        appMsgEntity.setRelatedBusinessType(appMsgType);
                        appMsgEntity.setRelatedBusinessId(dataMap.get("handleId").toString());

                        appMsgEntity.setEmployeeId((Integer) dataMap.get("dealEmployeeId"));


                        if (1  <= appMsgUtils.checkIsExistByTypeAndId(appMsgEntity.getRelatedBusinessType(), appMsgEntity.getRelatedBusinessId())) {


                        } else {
                            allMsgEntityList.add(appMsgEntity);

                        }

                    }


                }


            }

            //for Each 完毕  批量插入


            if(allPhoneMessageList.size()>0){
                result+=" 插入了"+allPhoneMessageList.size()+"  个 短信数据";
                phoneMessageDao.batchSavePhoneInfo(allPhoneMessageList);

            }
            if(allMsgEntityList.size()>0){

                result+=" 插入了"+allMsgEntityList.size()+"  个 站内消息数据";
                appMsgUtils.batchSaveAppMsgContent(allMsgEntityList);

            }

            if("".equals(result)){

                result+="没有插入任何数据";

            }

        }

        return result;

    }


}
