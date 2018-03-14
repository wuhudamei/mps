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


@Service
public class BizOrderReportBusinessService {

	Logger logger = Logger.getLogger(BizOrderReportBusinessService.class);

	@Autowired
	BizOrderReportSendRuleServiceDao orderReportSendRuleServiceDao;

	String customerRequestURL = "/CustomerAPI/GetCustomerIsExist";

	String resourceRequestURL = "/CustomerAPI/GetResourceIsExist";

	String createCustomerRequestURL = "/CustomerAPI/CreateNewCustomer";

	final String RESPONSE_SUCCESS_CODE = "1";
	private Lock lock = new ReentrantLock();    

	public boolean orderReportEffectivenessCheck(String storeId,String customerMobile){
		boolean result = false;

		Map<String,String> params=new HashMap<String,String>();
		params.put("storeId", storeId);
		params.put("customerMobile", customerMobile);

		String[] paramArr=new String[]{"storeId:"+storeId,"customerMobile:"+customerMobile};
		params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));


		Map<String, Object> customerResponse = httpRequest(customerRequestURL, params);
		if( RESPONSE_SUCCESS_CODE.equals( customerResponse.get("code").toString() ) ){
			if( "0".equals( customerResponse.get("isExist").toString() ) ){

				Map<String, Object> resourceResponse = httpRequest(resourceRequestURL, params);
				if( RESPONSE_SUCCESS_CODE.equals( resourceResponse.get("code").toString() ) ){
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


	@Transactional
	public BizEmployee orderReportDistributionCustomerService(BizOrderReport orderReport){
		

		if(null==orderReport.getServiceName()) {

			Map<String, Object> currentDistribution = orderReportSendRuleServiceDao.selectOrderReportDistributionCustomer();
			if (currentDistribution != null) {
				Integer sendRuleId = Integer.parseInt(currentDistribution.get("send_rule_id").toString());
				Integer max_service_index = Integer.parseInt(currentDistribution.get("max_service_index").toString());
				Integer service_index = Integer.parseInt(currentDistribution.get("service_index").toString());


				if (service_index > 0) {
					orderReportSendRuleServiceDao.updateBySendRuleId(sendRuleId);
				}

				if (service_index < max_service_index) {
					service_index = service_index + 1;
				} else {
					service_index = 1;
				}

				Map<String, Object> param = new HashMap<>();
				param.put("sendRuleId", sendRuleId);
				param.put("serviceIndex", service_index);

				orderReportSendRuleServiceDao.distributionCustomer(param);


				BizEmployee employee = orderReportSendRuleServiceDao.selectCustomerServiceByMap(param);


				if (employee != null) {

					Map<String, String> params = new HashMap<String, String>();
					params.put("storeId", employee.getStoreid());
					params.put("customerMobile", orderReport.getCustomerPhone());
					params.put("customerName", orderReport.getCustomerName());
					params.put("serviceId", employee.getEmployeeIdMappingOrderChangeSys());

					String[] paramArr = new String[]{"storeId:" + employee.getStoreid(), "customerMobile:" + orderReport.getCustomerPhone(), "customerName:" + orderReport.getCustomerName(), "serviceId:" + employee.getEmployeeIdMappingOrderChangeSys()};
					params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));

					Map<String, Object> response = httpRequest(createCustomerRequestURL, params);
					if (RESPONSE_SUCCESS_CODE.equals(response.get("code").toString())) {
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


			Map<String, String> params = new HashMap<String, String>();
			params.put("storeId", orderReport.getStoreId()==null?null:String.valueOf(orderReport.getStoreId()));
			params.put("customerMobile", orderReport.getCustomerPhone());
			params.put("customerName", orderReport.getCustomerName());
			params.put("serviceId", orderReport.getServiceMappingId());

			String[] paramArr = new String[]{"storeId:" +orderReport.getStoreId(), "customerMobile:" + orderReport.getCustomerPhone(), "customerName:" + orderReport.getCustomerName(), "serviceId:" +orderReport.getServiceMappingId()};
			params.put("key", KeyAuthenticateUtils.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY));

			Map<String, Object> response = httpRequest(createCustomerRequestURL, params);
			if (RESPONSE_SUCCESS_CODE.equals(response.get("code").toString())) {
				logger.info("接收客户信息接口调用成功！！！");
			}
			
			return null;
		}

	}
	

	private Map<String,Object> httpRequest(String requestURL,Map<String,String> params){
		String post = null;
		try {
			post = HttpConnection.post(PicRootName.getConfigValue("remote_interface_url") + requestURL, params);
		}catch(Exception e){
			logger.error("接口调用异常！！！【"+requestURL+"】");
			logger.error(e.getStackTrace());
		}
		

		return JsonUtils.parseJSON2Map(post);
	}

}
