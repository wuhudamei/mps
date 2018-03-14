package cn.damei.Quartz;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.MD5Utils;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;

import cn.damei.dao.modules.PrePareOrderMappingDao;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.BizPrepareOrderDao;
import cn.damei.entity.modules.BizPrepareOrder;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class QuarzPrepareOrder {
	private static Logger logger = LoggerFactory.getLogger(QuarzPrepareOrder.class);
	
	@Autowired
	private PrePareOrderMappingDao orderMappingDao;
	@Autowired
	private BizPrepareOrderDao bizPrepareOrderDao;

	@Autowired
	private BizSynDataService bizSynDataService;
	
	public  void  execute() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		User user = UserUtils.getUser();
		Date date = new Date();
		

		List<OrderJsonMapping>dataField = orderMappingDao.getDateTypeByDateField();
	

		List<OrderJsonMapping> list = orderMappingDao.getPreapareOrderJsonAuto();
		logger.debug("list长度："+list.size());			
		if(list.size()>0){
		    for (OrderJsonMapping orderJsonMapping : list) {
		    	logger.debug("订单流转系统   获取到的json串："+orderJsonMapping.getDataJson()+"时间："+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));			
				

				JSONArray json = JSONArray.fromObject(orderJsonMapping.getDataJson()); 
				
				logger.debug("json的长度："+json.size());
				

				BizPrepareOrder order =  new BizPrepareOrder();
				
				if(json.size()>0){
				    for(int i=0;i<json.size();i++){
				    					    						   
				        JSONObject prepareOrder = json.getJSONObject(i);

				        for (OrderJsonMapping mappingValue : dataField) {

					        if(prepareOrder.get("store_id").equals(mappingValue.getDataFrom())){
					        	order.setStoreId(mappingValue.getDataTo()==null?null:(Integer.valueOf(mappingValue.getDataTo().toString())));
					        }

					        if(prepareOrder.get("project_mode").equals(mappingValue.getDataFrom())){
					        	order.setProjectMode(mappingValue.getDataTo());
					        }

					        if(prepareOrder.get("customer_type").equals(mappingValue.getDataFrom())){
					        	order.setCustomerType(mappingValue.getDataTo());
					        }





					        
					        if(prepareOrder.get("house_type").toString().subSequence(0, 1).equals(mappingValue.getDataFrom())){
					        	order.setHouseType(mappingValue.getDataTo());
					        }
				        }
				    	order.setSaleType(prepareOrder.get("sale_type")==null?"":(prepareOrder.get("sale_type").toString()));

				        order.setOrderNumber(prepareOrder.get("order_number")==null?"":(prepareOrder.get("order_number").toString()));

				        order.setEnginDepartId(prepareOrder.get("engin_depart_id").equals("")?null:(Integer.parseInt(prepareOrder.get("engin_depart_id").toString())));

				        order.setContractNumber(prepareOrder.get("contract_number")==null?null:(prepareOrder.get("contract_number").toString()));
				        

				        order.setCustomerDescription(prepareOrder.get("customer_description")==null?null:(prepareOrder.get("customer_description").toString()));

				        order.setCustomerName(prepareOrder.get("customer_name")==null?null:(prepareOrder.get("customer_name").toString()));

				        order.setCustomerPhone(prepareOrder.get("customer_phone")==null?null:(prepareOrder.get("customer_phone").toString()));

				        order.setCustomerAddress(prepareOrder.get("customer_address")==null?null:(prepareOrder.get("customer_address").toString()));

				        order.setIsLongwayCommission(prepareOrder.get("is_longway_commission")==null?null:(prepareOrder.get("is_longway_commission").toString()));

				        order.setLongwayAmount(prepareOrder.get("longway_amount").equals("")?null:(Double.valueOf(prepareOrder.get("longway_amount").toString())));

				        order.setCommunityName(prepareOrder.get("community_name")==null?null:(prepareOrder.get("community_name").toString()));

				        order.setDetailAddress(prepareOrder.get("detail_address")==null?null:(prepareOrder.get("detail_address").toString()));

				        order.setBuildNumber(prepareOrder.get("build_number")==null?null:(prepareOrder.get("build_number").toString()));

				        order.setBuildUnit(prepareOrder.get("build_unit")==null?null:(prepareOrder.get("build_unit").toString()));

				        order.setBuildRoom(prepareOrder.get("build_room")==null?null:(prepareOrder.get("build_room").toString()));

				        order.setMapCoordinate(prepareOrder.get("map_coordinate")==null?null:(prepareOrder.get("map_coordinate").toString()));

				        order.setProvince(prepareOrder.get("province")==null?null:(prepareOrder.get("province").toString()));

				        order.setCity(prepareOrder.get("city")==null?null:(prepareOrder.get("city").toString()));

				        order.setCounty(prepareOrder.get("county")==null?null:(prepareOrder.get("county").toString()));
				        

				        order.setBuildType(prepareOrder.get("build_type")==null?null:(prepareOrder.get("build_type").toString()));



				        order.setHouseIsNew(prepareOrder.get("house_is_new")==null?null:(prepareOrder.get("house_is_new").toString()));

				        order.setIsElevator(prepareOrder.get("is_elevator")==null?null:(prepareOrder.get("is_elevator").toString()));

				        order.setDesignerName(prepareOrder.get("designer_name")==null?null:(prepareOrder.get("designer_name").toString()));

				        order.setDesignerPhone(prepareOrder.get("designer_phone")==null?null:(prepareOrder.get("designer_phone").toString()));

				        order.setOrderReporterName(prepareOrder.get("order_reporter_name")==null?null:(prepareOrder.get("order_reporter_name").toString()));

				        order.setOrderReporterPhone(prepareOrder.get("order_reporter_phone")==null?null:(prepareOrder.get("order_reporter_phone").toString()));

				        order.setServiceName(prepareOrder.get("service_name")==null?null:(prepareOrder.get("service_name").toString()));

				        order.setServicePhone(prepareOrder.get("service_phone")==null?null:(prepareOrder.get("service_phone").toString()));

				        order.setContractStartDate(prepareOrder.get("contract_start_date").equals("")?null:(DateUtils.parseDate(prepareOrder.get("contract_start_date").toString())));

				        order.setContractEndDate(prepareOrder.get("contract_end_date").equals("")?null:(DateUtils.parseDate(prepareOrder.get("contract_end_date").toString())));

				        order.setCoveredArea(prepareOrder.get("covered_area")==null?null:(prepareOrder.get("covered_area").toString()));

				        order.setContractArea(prepareOrder.get("contract_area")==null?null:(prepareOrder.get("contract_area").toString()));

				        order.setContractTime(prepareOrder.get("contract_time").equals("")?null:(Integer.valueOf(prepareOrder.get("contract_time").toString())));

				        order.setSignContractDate(prepareOrder.get("sign_contract_date").equals("")?null:(DateUtils.parseDate(prepareOrder.get("sign_contract_date").toString())));

				        order.setBizOrderAcceptArea(prepareOrder.get("biz_order_accept_area")==null?null:(prepareOrder.get("biz_order_accept_area").toString()));

				        

				        order.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_10);

				        order.setSynDataId(orderJsonMapping.getDataId());

				        order.setContractAmount(prepareOrder.get("contract_amount").equals("")?null:(Double.valueOf(prepareOrder.get("contract_amount").toString())));

				        String customerType = prepareOrder.get("customer_level").equals("")?"":(prepareOrder.get("customer_level").toString());

				        order.setAuditorName(prepareOrder.get("auditor_realname").equals("")?"":(prepareOrder.get("auditor_realname").toString()));

				        order.setAuditorPhone(prepareOrder.get("auditor_mobile").equals("")?"":(prepareOrder.get("auditor_mobile").toString()));

				        order.setAuditorTagname(prepareOrder.get("tagname").equals("")?"":(prepareOrder.get("tagname").toString()));

				        String empId = bizPrepareOrderDao.findAuditorId(order.getAuditorName(),order.getAuditorPhone(),8);
				        if(empId!=null){
				        	order.setAuditorEmployeeId(Integer.parseInt(empId));
				        }
				        
				        if(null!=customerType ){
				        	if(customerType.equals("1")){
				        		order.setCustomerType(OrderConstantUtil.ORDER_CUS_TYPE_ORDINARY_1);
				        	}else if(customerType.equals("2")){
				        		order.setCustomerType(OrderConstantUtil.ORDER_CUS_TYPE_VIP_0);
				        	}else if(customerType.equals("3")){
				        		order.setCustomerType(OrderConstantUtil.ORDER_CUS_TYPE_SINGLE_2);
				        	}
				        }
				        
				        order.setCreateDate(date);
				        order.setUpdateDate(date);
				        order.setCreateBy(user);
				        order.setUpdateBy(user);
				        order.setDelFlag("0");
				        
				        logger.debug("获取的门店======"+prepareOrder.get("store_id")+",,,,order对象中的门店===="+order.getStoreId());
				        logger.debug("获取的工程模式======"+prepareOrder.get("project_mode")+",,,,order对象中的工程模式===="+order.getProjectMode());
				        logger.debug("获取的订单编号======"+prepareOrder.get("order_number")+",,,,order对象中的订单编号===="+order.getOrderNumber());
				        logger.debug("获取的区域======"+prepareOrder.get("engin_depart_id")+",,,,order对象中的区域===="+order.getEnginDepartId());
				        logger.debug("获取的合同编号======"+prepareOrder.get("contract_number")+",,,,order对象中的合同编号===="+order.getContractNumber());
				        
				        logger.debug("获取的客户类型======"+prepareOrder.get("customer_type")+",,,,order对象中的客户类型===="+order.getCustomerType());
				        logger.debug("获取的客户属性描述======"+prepareOrder.get("customer_description")+",,,,order对象中的客户属性描述===="+order.getCustomerDescription());
				        logger.debug("获取的客户姓名======"+prepareOrder.get("customer_name")+",,,,order对象中的客户姓名===="+order.getCustomerName());
				        logger.debug("获取的客户手机======"+prepareOrder.get("customer_phone")+",,,,order对象中的客户手机===="+order.getCustomerPhone());
				        logger.debug("获取的客户地址======"+prepareOrder.get("customer_address")+",,,,order对象中的客户地址===="+order.getCustomerAddress());


						try {
							bizPrepareOrderDao.insert(order);

						} catch (Exception e) {
							e.printStackTrace();
							logger.error("===================预备订单json获取时,插入预备订单表中时异常=====================该预备订单id为:  "+order.getOrderNumber());
							logger.error("===================预备订单json获取时,插入预备订单表中时异常=====================该预备订单id为:  "+order.getOrderNumber());
							logger.error("===================预备订单json获取时,插入预备订单表中时异常=====================该预备订单id为:  "+order.getOrderNumber());
							String reason = "订单json串数据异常,插入失败  预备订单编号为 +"+order.getOrderNumber()+"+ 支持空数据和null  但 请查看是否数据过长,类型一致等设计问题";

							logger.error("===================订单json串数据异常,插入失败  预备订单编号为  "+order.getOrderNumber()+" 支持空数据和null  但 请查看是否数据过长,类型一致等设计问题");
							logger.error("===================订单json串数据异常,插入失败  预备订单编号为  "+order.getOrderNumber()+" 支持空数据和null  但 请查看是否数据过长,类型一致等设计问题");
							logger.error("===================订单json串数据异常,插入失败  预备订单编号为  "+order.getOrderNumber()+" 支持空数据和null  但 请查看是否数据过长,类型一致等设计问题");


							Map<String,String> jsonMap = new HashMap<String,String>();
							jsonMap.put("orderId",order.getOrderNumber());
							jsonMap.put("time", DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
							jsonMap.put("remarks",reason);
							jsonMap.put("status",ConstantUtils.PREPARE_ORDER_STATUS_20);
							String key;
							key = MD5Utils.MD5Secret(jsonMap);

							jsonMap.put("key",key);
							BizSynData bizSynData = new BizSynData();
							bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
							bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_101);

							bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
							bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
							bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
							bizSynData.preInsert();
							bizSynDataService.save(bizSynData);




						}finally {

							orderMappingDao.updateSynDataStatus(orderJsonMapping.getDataId(), ConstantUtils.SYN_STATUS_3);

						}

				    }
				 }
				


				
				
		}
		
		 }else{
			 
			 logger.debug("暂无需要接收的数据:  +当前时间:  " +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) );
		 }
				
				
				
			 
			 
			 
		}
}
