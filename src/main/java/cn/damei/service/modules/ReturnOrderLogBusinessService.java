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



@Service
@Transactional
public class ReturnOrderLogBusinessService {


    private Logger log = LoggerFactory.getLogger(ReturnOrderLogBusinessService.class);
    @Autowired
    private BizOrderReportBusinessService checkService;
    @Autowired
    private BizBusinessStatusLogDao logDao;




    @Autowired
    private BizOrderReportLogDao orderReportLogDao;

    @Transactional(readOnly = false)
    public  BizOrderReport checkOrderReportIsExist(BizOrderReport bizOrderReport) {


        try{

        boolean checkReportIsExist = checkService.orderReportEffectivenessCheck(String.valueOf(bizOrderReport.getStoreId()), bizOrderReport.getCustomerPhone());
        if (checkReportIsExist) {


            BizEmployee employee = checkService.orderReportDistributionCustomerService(bizOrderReport);
            if (null != employee) {
                bizOrderReport.setServiceEmployeeId(employee.getId());
                bizOrderReport.setServiceName(employee.getRealname());
                bizOrderReport.setServicePhone(employee.getPhone());
                bizOrderReport.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_25);
                bizOrderReport.setLogType(BizOrderReportConstantUtil.SEND_LOG_TYPE_1);




                bizOrderReport.preInsert();






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
