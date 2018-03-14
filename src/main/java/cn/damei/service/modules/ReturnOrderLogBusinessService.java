package cn.damei.service.modules;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.dao.modules.BizOrderReportLogDao;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by joseph on 2017/5/10.
 * 返单校验及log处理
 *
 * 分派客服log
 * 1:biz_order_report_send_log
 *
 * 进店log
 * 2:biz_order_report_instore_log
 *
 * 签单log
 * 3:biz_order_report_sign_log
 */

@Service
@Transactional
public class ReturnOrderLogBusinessService {


    private Logger log = LoggerFactory.getLogger(ReturnOrderLogBusinessService.class);
    @Autowired
    private BizOrderReportBusinessService checkService;
    @Autowired
    private BizBusinessStatusLogDao logDao;

    /**
     * 保存分派客服log
     * @param bizOrderReport
     *//*
    public void  saveReportSendLog(BizOrderReport bizOrderReport){
        Map<String,Object> sendLogMap = new HashMap<>();

            sendLogMap.put("sendType", bizOrderReport.getLogType());
            sendLogMap.put("orderReportId",bizOrderReport.getId());
            sendLogMap.put("serviceEmployeeId",bizOrderReport.getServiceEmployeeId());
            sendLogMap.put("serviceName",bizOrderReport.getServiceName());
            sendLogMap.put("servicePhone",bizOrderReport.getServicePhone());
            sendLogMap.put("operateDateTime",bizOrderReport.getCreateDate());
            sendLogMap.put("operateEmployeeId",bizOrderReport.getCreateBy()==null?null:bizOrderReport.getCreateBy().getId());

            logDao.saveSendLog(sendLogMap);

    }
    *//**
     * 保存进店log
     * @param bizOrderReport
     *//*
    public void  saveReportInStoreLog(BizOrderReport bizOrderReport){

        logDao.saveInstoreLog(bizOrderReport);




    }
    *//**
     * 保存签单log
     * @param bizOrderReport
     *//*
    public void  saveReportSignLog(BizOrderReport bizOrderReport){

        logDao.saveSignLog(bizOrderReport);



    }
*/


    @Autowired
    private BizOrderReportLogDao orderReportLogDao;
    /**
     * 传入刚申请申请的返单
     * @param bizOrderReport
     * @return
     * 返回校验过的添加log日志的返单
     */
    @Transactional(readOnly = false)
    public  BizOrderReport checkOrderReportIsExist(BizOrderReport bizOrderReport) {


        try{
        //校验返单是否有效
        boolean checkReportIsExist = checkService.orderReportEffectivenessCheck(String.valueOf(bizOrderReport.getStoreId()), bizOrderReport.getCustomerPhone());
        if (checkReportIsExist) {

            //客服id,realName,phone
            BizEmployee employee = checkService.orderReportDistributionCustomerService(bizOrderReport);
            if (null != employee) {
                bizOrderReport.setServiceEmployeeId(employee.getId());
                bizOrderReport.setServiceName(employee.getRealname());
                bizOrderReport.setServicePhone(employee.getPhone());
                bizOrderReport.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_25);
                bizOrderReport.setLogType(BizOrderReportConstantUtil.SEND_LOG_TYPE_1);
                //分配客服完毕

                //记录分配日志

                bizOrderReport.preInsert();




                //插入分配客服log
                // 返单上报日志
                OrderReportLogEntity logEntity = new OrderReportLogEntity();


                logEntity.setOrderReportId(bizOrderReport.getId());
                logEntity.setLogType(BizOrderReportConstantUtil.DISTRIBUTE_SERVICE_1);
                logEntity.setLogDateTime(new Date());
                logEntity.setServiceId(employee.getId());
                logEntity.setServiceName(employee.getRealname());
                logEntity.setServicePhone(employee.getPhone());
                logEntity.setOperateDateTime(new Date());
                logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
                logEntity.setOperateEmployeeId(UserUtils.getUser().getId());
                orderReportLogDao.saveSendLog(logEntity);


                return bizOrderReport;

            } else {

                return bizOrderReport;
            }


        } else {

            //无效 插入无效log

            BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
            bizBusinessStatusLog.setBusinessRemarks(BizOrderReportConstantUtil.REPORT_STATUS_20_WORD);
            bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_501);
            bizBusinessStatusLog.setBusinessOnlyMarkInt(bizOrderReport.getId());
            bizBusinessStatusLog.setBusinessStatus(BizOrderReportConstantUtil.REPORT_STATUS_20);
            if (StringUtils.isNotBlank(UserUtils.getUser().getId())){
                bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getId()));
            }
            bizBusinessStatusLog.setStatusDatetime(new Date());
            bizBusinessStatusLog.preInsert();
            logDao.insert(bizBusinessStatusLog);


            bizOrderReport.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_20);
            return bizOrderReport;
        }

    }catch(Exception e){
            e.printStackTrace();
            throw  e ;
        }


    }

}
