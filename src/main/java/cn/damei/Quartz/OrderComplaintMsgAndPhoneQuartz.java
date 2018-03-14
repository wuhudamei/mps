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



@Service
public class OrderComplaintMsgAndPhoneQuartz {


    private static final String appMsgType = AppMsgTypeConstant.ORDER_COMPLAINT_URGE_TYPE_1000;
    private static final String phoneMessageType = PhoneMessageTypeConstant.ORDER_COMPLAINT_URGE_TYPE_1000;

    private static final String phoneMessageStatus = PhoneMessageTypeConstant.SEND_MSG_STATUS_0;


    private String customerName = "";
    private String customerInfo = "";
    private String problemName = "";


    private static final String appMsgTitle = "工程投诉超时提醒";



    @Autowired
    private PhoneMessageDao phoneMessageDao;



    @Autowired
    private AppMsgUtils appMsgUtils;


    @Autowired
    private OrderComplaintMsgAndPhoneQuartzDao dao;


    public void execute() {
        Date currentDate = new Date();
        List<AppMsgEntity> allMsgEntityList = new ArrayList<>(48);
        List<PhoneMessageEntity> allPhoneMessageList = new ArrayList<>(48);



        List<Map<String, Object>> allDataInfo = dao.getAllDataForQuartz();
        int resultSize = allDataInfo.size();
        if (resultSize > 0) {


            for (int i = 0; i < resultSize; i++) {
                Map<String,Object> dataMap = allDataInfo.get(i);
                String responseTime = allDataInfo.get(i).get("responseTime").toString();



                if (isNumeric(responseTime)) {


                    Date problemCreateDate = (Date) dataMap.get("createDate");

                    Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());

                    if (currentDate.getTime() > problemDelayDate.getTime()) {





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

































    public String execute2() {
        String result ="";
        Date currentDate = new Date();
        List<AppMsgEntity> allMsgEntityList = new ArrayList<>(48);
        List<PhoneMessageEntity> allPhoneMessageList = new ArrayList<>(48);



        List<Map<String, Object>> allDataInfo = dao.getAllDataForQuartz();
        int resultSize = allDataInfo.size();
        if (resultSize > 0) {


            for (int i = 0; i < resultSize; i++) {

                Map<String,Object> dataMap = allDataInfo.get(i);

                String responseTime = dataMap.get("responseTime").toString();



                if (isNumeric(responseTime)) {


                    Date problemCreateDate = (Date) dataMap.get("createDate");

                    Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());

                    if (currentDate.getTime() > problemDelayDate.getTime()) {





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
