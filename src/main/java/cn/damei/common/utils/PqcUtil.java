package cn.damei.common.utils;

import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;




public class PqcUtil {


    private static final String MESSAGE_TYPE = "600201";

    private static final String MESSAGE_STATUS = "0";

    private static final String APP_MSG_TITLE = "质检员产生复检单";

    private static final String MSG_TYPE = MessagePushType.MSG_TYPE_1;
    private static final String APPLY_CHECK_BILL_MSG_BUSINESS_TYPE = MessagePushType.MESSAGE_PUSH_TYPE_103001001;
    private static final String SELECT_CHECK_BILL_MSG_BUSINESS_TYPE = MessagePushType.MESSAGE_PUSH_TYPE_103001002;



    @Autowired
    private  PhoneMessageDao messageDao;

    @Autowired
    private  BizProjectChangeBillService msgService;


    public  boolean saveMessage(String messageContent, Integer receiveEmployeeId, String receivePhone, Integer relatedBusinessId) {

            PhoneMessageEntity entity = new PhoneMessageEntity();

        entity.setMessageGenerateTime(new Date());
        entity.setMessageContent(messageContent);
        entity.setReceiveEmployeeId(receiveEmployeeId);
        entity.setReceivePhone(receivePhone);
        entity.setStatus(MESSAGE_STATUS);
        entity.setRelatedBusinessType(MESSAGE_TYPE);
        entity.setRelatedBusinessId(relatedBusinessId);
        try {
            messageDao.saveMessageContent(entity);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }


        return true;
    }



    public  boolean saveAppMsg(String msgContent, Integer businessId, Integer employeeId, boolean checkDiff) {


            BizMsg  bizMsg = new BizMsg();


        bizMsg.setMsgTitle(APP_MSG_TITLE);
        bizMsg.setMsgTime(new Date());
        bizMsg.setMsgContent(msgContent);
        bizMsg.setMsgType(MSG_TYPE);

        bizMsg.setBusiType(checkDiff ? APPLY_CHECK_BILL_MSG_BUSINESS_TYPE : SELECT_CHECK_BILL_MSG_BUSINESS_TYPE);
        bizMsg.setBusiIdInt(businessId);
        bizMsg.setEmployeeId(employeeId);
        try {

            msgService.saveBizMsg(bizMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }



    public static   List<ReportCheckDetailsPic> HandleSavePics(String [] photos ,HttpServletRequest request,Integer inspectBillId,String type){
     String  uploadDir = "";

       if("2".equals(type)){
           uploadDir= ConstantUtils.UPLOAD_SELECTCHECKITEM;

       }else if("3".equals(type)){
            uploadDir= ConstantUtils.UPLOAD_CONFIRM;

        }

        List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
        Date date = new Date();


        for(String p : photos){

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");


            String rootPath = request.getSession().getServletContext().getRealPath("");
            File filePath = new File(rootPath + uploadDir+ DateUtils.getDate1());

            if(!filePath.exists() && !filePath.isDirectory()){
                filePath.mkdirs();
            }
            String filepath = filePath + filePath.separator + uuid + ".jpeg";
            Base64Util.generateImage(p, filepath);

            String picpath = uploadDir+ DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";

            ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
            reportCheckDetailsPic.setBusinessIdInt(inspectBillId);
            reportCheckDetailsPic.setBusinessType(type);
            reportCheckDetailsPic.setPicUrl(picpath);
            reportCheckDetailsPic.setPicDatetime(date);
            reportCheckDetailsPic.setCreateDate(date);
            reportCheckDetailsPic.setUpdateDate(date);
            reportCheckDetailsPic.setDelFlag("0");
            pList.add(reportCheckDetailsPic);

        }
        return pList;




    }

    public static double doubleAdd(Double v1, Double v2) {
        if (null == v1) {

            v1 = 0d;
        }
        if (null == v2) {

            v2 = 0d;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

}
