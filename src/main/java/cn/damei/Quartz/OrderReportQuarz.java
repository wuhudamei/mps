package cn.damei.Quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.service.modules.BizOrderReportService;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 返单定时器
 * @author hyh
 *
 */
public class OrderReportQuarz {
	@Autowired
	 private BizOrderReportService bizOrderReportService;
	@Autowired
	private BizBusinessStatusLogDao logDao;
	 public void execute(){
		 try {
			 Date date = new Date();
			 BizOrderReport bizOrderReport = new BizOrderReport();
			 List<BizOrderReport> orderReportList = bizOrderReportService.findList(bizOrderReport);
             if(orderReportList!=null && orderReportList.size()>0){
            	 for(BizOrderReport orderReport:orderReportList){
            		 Date reportDatetime = orderReport.getReportDatetime();
            		 Calendar calendar = Calendar.getInstance();
            		 calendar.setTime(reportDatetime);
            		 calendar.add(Calendar.MONTH, 2);
            		 if(calendar.getTime().getTime()<date.getTime()){//过期
            			 if(orderReport.getReportStatus().equals("10")||orderReport.getReportStatus().equals("25")||orderReport.getReportStatus().equals("26")||orderReport.getReportStatus().equals("30")||orderReport.getReportStatus().equals("90")){
            				 orderReport.setReportStatus("90");
	            			 bizOrderReportService.save(orderReport);
							 bizOrderReport.setUpdateDate(new Date());
	            			
							 //
							 //过期
							 BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
							 bizBusinessStatusLog.setBusinessRemarks(BizOrderReportConstantUtil.REPORT_STATUS_90_WORD);
							 bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_501);
							 bizBusinessStatusLog.setBusinessOnlyMarkInt(bizOrderReport.getId());
							 bizBusinessStatusLog.setBusinessStatus(BizOrderReportConstantUtil.REPORT_STATUS_90);
							 if (StringUtils.isNotBlank(UserUtils.getUser().getId())){
								 bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getId()));
							 }
							 bizBusinessStatusLog.setStatusDatetime(new Date());
							 bizBusinessStatusLog.preInsert();
							 logDao.insert(bizBusinessStatusLog);
	            		 }


            		 }
    			 }
             }
            
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
}
