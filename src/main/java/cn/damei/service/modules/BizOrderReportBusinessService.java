package cn.damei.service.modules;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.HttpConnection;
import cn.damei.common.utils.JsonUtils;
import cn.damei.common.utils.KeyAuthenticateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.dao.modules.BizOrderReportSendRuleServiceDao;

/**
 * 返单相关实际业务处理Service，1、验证返单是否有效；2、为返单分配客服人员
 * @author Liwancai
 * @version 2017-05-06
 */
@Service
public class BizOrderReportBusinessService {

	Logger logger = Logger.getLogger(BizOrderReportBusinessService.class);

	@Autowired
	BizOrderReportSendRuleServiceDao orderReportSendRuleServiceDao;
	//客户池验证地址
	String customerRequestURL = "/CustomerAPI/GetCustomerIsExist";
	//资源池验证地址
	String resourceRequestURL = "/CustomerAPI/GetResourceIsExist";
	//接收客户信息接口
	String createCustomerRequestURL = "/CustomerAPI/CreateNewCustomer";
	//接口调用成功
	final String RESPONSE_SUCCESS_CODE = "1";
	private Lock lock = new ReentrantLock();    
	/**
	 * 返单有效性校验，调用第三方接口(客户池、资源池)，根据返回结果验证有效性，当接口调用异常失败时，默认返回结果为true
	 * @param storeId
	 * @param customerMobile
	 * @return
	 */
	public boolean orderReportEffectivenessCheck(String storeId,String customerMobile){
		boolean result = false;

		Map<String,String> params=new HashMap<String,String>();
		params.put("storeId", storeId);
		params.put("customerMobile", customerMobile);

		String[] paramArr=new String[]{"storeId:"+storeId,"customerMobile:"+customerMobile};
		params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));

		//先验证客户池，如果客户池不存在则验证资源池，如果都不存在返回true，任意一个存在都返回false
		Map<String, Object> customerResponse = httpRequest(customerRequestURL, params);
		if( RESPONSE_SUCCESS_CODE.equals( customerResponse.get("code").toString() ) ){//调用成功
			if( "0".equals( customerResponse.get("isExist").toString() ) ){
				//继续验证资源池
				Map<String, Object> resourceResponse = httpRequest(resourceRequestURL, params);
				if( RESPONSE_SUCCESS_CODE.equals( resourceResponse.get("code").toString() ) ){//调用成功
					if( "0".equals( resourceResponse.get("isExist").toString() ) ){
						result = true;
					}
				}else{
					result = true;
					logger.info("资源池验证接口调用失败！！！");
					logger.error(resourceResponse.get("message"));
				}
			}
		}else{
			result = true;
			logger.info("客户池验证接口调用失败！！！");
			logger.error(customerResponse.get("message"));
		}
		return result;
	}

	/**
	 * 根据当前执行的轮循规则为返单分配客服人员，返回分配的客服人员信息
	 * 如果传入客服对象不为空,则为重新分配客服
	 * @return
	 */
	@Transactional
	public BizEmployee orderReportDistributionCustomerService(BizOrderReport orderReport){
		
		//分配客服
		if(null==orderReport.getServiceName()) {
			//1、查询当前接收任务的客服ID
			Map<String, Object> currentDistribution = orderReportSendRuleServiceDao.selectOrderReportDistributionCustomer();
			if (currentDistribution != null) {
				Integer sendRuleId = Integer.parseInt(currentDistribution.get("send_rule_id").toString());
				Integer max_service_index = Integer.parseInt(currentDistribution.get("max_service_index").toString());
				Integer service_index = Integer.parseInt(currentDistribution.get("service_index").toString());

				//清除当前分配标示
				if (service_index > 0) {
					orderReportSendRuleServiceDao.updateBySendRuleId(sendRuleId);
				}

				if (service_index < max_service_index) {
					service_index = service_index + 1;
				} else {
					service_index = 1;
				}
				//2、更新分配信息
				Map<String, Object> param = new HashMap<>();
				param.put("sendRuleId", sendRuleId);
				param.put("serviceIndex", service_index);

				orderReportSendRuleServiceDao.distributionCustomer(param);

				//3、查询客服对象
				BizEmployee employee = orderReportSendRuleServiceDao.selectCustomerServiceByMap(param);

				//判断是否为空，如果不为空，调用接口通知对接系统
				if (employee != null) {

					Map<String, String> params = new HashMap<String, String>();
					params.put("storeId", employee.getStoreid());
					params.put("customerMobile", orderReport.getCustomerPhone());
					params.put("customerName", orderReport.getCustomerName());
					params.put("serviceId", employee.getEmployeeIdMappingOrderChangeSys());

					String[] paramArr = new String[]{"storeId:" + employee.getStoreid(), "customerMobile:" + orderReport.getCustomerPhone(), "customerName:" + orderReport.getCustomerName(), "serviceId:" + employee.getEmployeeIdMappingOrderChangeSys()};
					params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));

					Map<String, Object> response = httpRequest(createCustomerRequestURL, params);
					if (RESPONSE_SUCCESS_CODE.equals(response.get("code").toString())) {//调用成功
						logger.info("接收客户信息接口调用成功！！！");
					}
					return employee;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}else{
			//转派客服

			Map<String, String> params = new HashMap<String, String>();
			params.put("storeId", orderReport.getStoreId()==null?null:String.valueOf(orderReport.getStoreId()));
			params.put("customerMobile", orderReport.getCustomerPhone());
			params.put("customerName", orderReport.getCustomerName());
			params.put("serviceId", orderReport.getServiceMappingId());

			String[] paramArr = new String[]{"storeId:" +orderReport.getStoreId(), "customerMobile:" + orderReport.getCustomerPhone(), "customerName:" + orderReport.getCustomerName(), "serviceId:" +orderReport.getServiceMappingId()};
			params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));

			Map<String, Object> response = httpRequest(createCustomerRequestURL, params);
			if (RESPONSE_SUCCESS_CODE.equals(response.get("code").toString())) {//调用成功
				logger.info("接收客户信息接口调用成功！！！");
			}
			
			return null;
		}

	}
	
	/**
	 * 请求接口，返回结果解析
	 * @param requestURL
	 * @param params
	 * @return
	 */
	private Map<String,Object> httpRequest(String requestURL,Map<String,String> params){
		String post = null;
		try {
			post = HttpConnection.post(PicRootName.getConfigValue("remote_interface_url") + requestURL, params);
		}catch(Exception e){
			logger.error("接口调用异常！！！【"+requestURL+"】");
			logger.error(e.getStackTrace());
		}
		
		//json结果 解析
		return JsonUtils.parseJSON2Map(post);
	}

}
