package cn.damei.Quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.dao.modules.BizOrderReportSendRuleDao;
import cn.damei.entity.modules.BizOrderReportSendRule;

/**
 * 返单客服轮训规则定时任务
 * @author Liwancai
 * @version 2017-05-0
 */
public class OrderReportSendRuleQuarz {
	
	Logger logger = Logger.getLogger(OrderReportSendRuleQuarz.class); 
	
	@Autowired
	private BizOrderReportSendRuleDao orderReportSendRuleDao;
	
	private final String PEDDING_EXECUTION = "0";	//待执行
	private final String ON_EXECUTION = "1";		//在执行
	private final String EXPIRED = "2";				//已过期
	
	/**
	 * 每天晚上零点执行，获取当前待执行的轮循规则
	 */
    public void execute(){
		logger.info("规则轮循定时任务：执行......begin......");
		List<BizOrderReportSendRule>  list =  orderReportSendRuleDao.selectByStatus(PEDDING_EXECUTION);
		if( list != null && list.size() > 0 ){
			BizOrderReportSendRule rule = list.get(0);
			Date currentTime = new Date();
			Date startTime = rule.getStartDatetime();
			
			/**
			 * 当前时间大于规则开始时间
			 * 更新当前正在执行的规则状态为已过期，设置结束时间
			 * 将待执行规则状态更新为正在执行
			 */
			if(PEDDING_EXECUTION.equals(rule.getStatus() )  && currentTime.getTime() >= startTime.getTime() ){
				Map<String,Object> params = new HashMap<>();
				params.put("setStatus", EXPIRED);
				params.put("oldStatus", ON_EXECUTION);
				params.put("endDatetime", currentTime);
				
				orderReportSendRuleDao.updateRuleStatusByMap(params);
				
				params.clear();
				params.put("setStatus", ON_EXECUTION);
				params.put("oldStatus", PEDDING_EXECUTION);
				orderReportSendRuleDao.updateRuleStatusByMap(params);
			}else{
				logger.info("规则轮循定时任务：当前无待执行规则或待执行规则未到开始时间。");
			}
		}else{
			logger.info("规则轮循定时任务：当前暂无待执行规则。");
		}
		logger.info("规则轮循定时任务：执行......end......");
    }
}
