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

/**
 * 
 * @author 梅浩
 * @2017年3月15日
 * @mdn美得你
 * @author_phone : 18610507472
 * @ClassInfo:该类: 将订单接收的json数据,取出并进行解析成Order对象
 * 并数据映射(如 beijing 映射为2)然后保存到预备订单表中的 的定时调度任务类
 */
public class QuarzPrepareOrder {
	private static Logger logger = LoggerFactory.getLogger(QuarzPrepareOrder.class);//日志
	
	@Autowired
	private PrePareOrderMappingDao orderMappingDao;//映射字段的接口查询 及预备订单json的查询
	@Autowired
	private BizPrepareOrderDao bizPrepareOrderDao;//映射字段的接口查询 及预备订单json的查询

	@Autowired
	private BizSynDataService bizSynDataService;  //如果数据异常 插入异常返回数据
	
	public  void  execute() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		User user = UserUtils.getUser();
		Date date = new Date();
		
		//查询的映射map  key:接收的json的value值     value: 对应的本系统中的业务外键id
		List<OrderJsonMapping>dataField = orderMappingDao.getDateTypeByDateField();
	
		//1 查询  syn_data 中的 json 数据 
		List<OrderJsonMapping> list = orderMappingDao.getPreapareOrderJsonAuto();
		logger.debug("list长度："+list.size());			
		if(list.size()>0){
		    for (OrderJsonMapping orderJsonMapping : list) {
		    	logger.debug("订单流转系统   获取到的json串："+orderJsonMapping.getDataJson()+"时间："+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));			
				
		    	// 首先把字符串转成 JSONArray  对象
				JSONArray json = JSONArray.fromObject(orderJsonMapping.getDataJson()); 
				
				logger.debug("json的长度："+json.size());
				
				//要保存的订单对象
				BizPrepareOrder order =  new BizPrepareOrder();
				
				if(json.size()>0){
				    for(int i=0;i<json.size();i++){
				    					    						   
				        JSONObject prepareOrder = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				        // 得到 每个对象中的属性值
				        for (OrderJsonMapping mappingValue : dataField) {
				        	//1.门店(映射)
					        if(prepareOrder.get("store_id").equals(mappingValue.getDataFrom())){
					        	order.setStoreId(mappingValue.getDataTo()==null?null:(Integer.valueOf(mappingValue.getDataTo().toString())));
					        }
					        //2.工程模式(映射)
					        if(prepareOrder.get("project_mode").equals(mappingValue.getDataFrom())){
					        	order.setProjectMode(mappingValue.getDataTo());
					        }
					        //6.客户类型(映射)
					        if(prepareOrder.get("customer_type").equals(mappingValue.getDataFrom())){
					        	order.setCustomerType(mappingValue.getDataTo());
					        }
				        	//22.套餐类型
//					        if(prepareOrder.get("sale_type").equals(mappingValue.getDataFrom())){
//					        	order.setSaleType(mappingValue.getDataTo());
//					        }
					        //24.户型
					        
					        if(prepareOrder.get("house_type").toString().subSequence(0, 1).equals(mappingValue.getDataFrom())){
					        	order.setHouseType(mappingValue.getDataTo());
					        }
				        }
				    	order.setSaleType(prepareOrder.get("sale_type")==null?"":(prepareOrder.get("sale_type").toString()));
				        //3.订单编号
				        order.setOrderNumber(prepareOrder.get("order_number")==null?"":(prepareOrder.get("order_number").toString()));
				        //4.区域
				        order.setEnginDepartId(prepareOrder.get("engin_depart_id").equals("")?null:(Integer.parseInt(prepareOrder.get("engin_depart_id").toString())));
				        //5.合同编号
				        order.setContractNumber(prepareOrder.get("contract_number")==null?null:(prepareOrder.get("contract_number").toString()));
				        
				        //7.客户属性描述
				        order.setCustomerDescription(prepareOrder.get("customer_description")==null?null:(prepareOrder.get("customer_description").toString()));
				        //8.客户姓名
				        order.setCustomerName(prepareOrder.get("customer_name")==null?null:(prepareOrder.get("customer_name").toString()));
				        //9.客户手机
				        order.setCustomerPhone(prepareOrder.get("customer_phone")==null?null:(prepareOrder.get("customer_phone").toString()));
				        //10.客户地址
				        order.setCustomerAddress(prepareOrder.get("customer_address")==null?null:(prepareOrder.get("customer_address").toString()));
				        //11.是否有远程费
				        order.setIsLongwayCommission(prepareOrder.get("is_longway_commission")==null?null:(prepareOrder.get("is_longway_commission").toString()));
				        //12.远程费金额
				        order.setLongwayAmount(prepareOrder.get("longway_amount").equals("")?null:(Double.valueOf(prepareOrder.get("longway_amount").toString())));
				        //13.小区名称
				        order.setCommunityName(prepareOrder.get("community_name")==null?null:(prepareOrder.get("community_name").toString()));
				        //14.详细地址
				        order.setDetailAddress(prepareOrder.get("detail_address")==null?null:(prepareOrder.get("detail_address").toString()));
				        //15.楼
				        order.setBuildNumber(prepareOrder.get("build_number")==null?null:(prepareOrder.get("build_number").toString()));
				        //16.单元
				        order.setBuildUnit(prepareOrder.get("build_unit")==null?null:(prepareOrder.get("build_unit").toString()));
				        //17.室
				        order.setBuildRoom(prepareOrder.get("build_room")==null?null:(prepareOrder.get("build_room").toString()));
				        //18.坐标
				        order.setMapCoordinate(prepareOrder.get("map_coordinate")==null?null:(prepareOrder.get("map_coordinate").toString()));
				        //19.省
				        order.setProvince(prepareOrder.get("province")==null?null:(prepareOrder.get("province").toString()));
				        //20.市
				        order.setCity(prepareOrder.get("city")==null?null:(prepareOrder.get("city").toString()));
				        //21.县
				        order.setCounty(prepareOrder.get("county")==null?null:(prepareOrder.get("county").toString()));
				        
				        //23.房屋类型--平层楼房，别墅，复式
				        order.setBuildType(prepareOrder.get("build_type")==null?null:(prepareOrder.get("build_type").toString()));
				        //24.户型
				        //order.setHouseType(prepareOrder.get("house_type")==null?null:(prepareOrder.get("house_type").toString()));
				        //25.新房，老房
				        order.setHouseIsNew(prepareOrder.get("house_is_new")==null?null:(prepareOrder.get("house_is_new").toString()));
				        //26.有无电梯
				        order.setIsElevator(prepareOrder.get("is_elevator")==null?null:(prepareOrder.get("is_elevator").toString()));
				        //27.设计师姓名
				        order.setDesignerName(prepareOrder.get("designer_name")==null?null:(prepareOrder.get("designer_name").toString()));
				        //28.设计师电话
				        order.setDesignerPhone(prepareOrder.get("designer_phone")==null?null:(prepareOrder.get("designer_phone").toString()));
				        //29.跟单员姓名
				        order.setOrderReporterName(prepareOrder.get("order_reporter_name")==null?null:(prepareOrder.get("order_reporter_name").toString()));
				        //30.跟单员电话
				        order.setOrderReporterPhone(prepareOrder.get("order_reporter_phone")==null?null:(prepareOrder.get("order_reporter_phone").toString()));
				        //31.客服姓名
				        order.setServiceName(prepareOrder.get("service_name")==null?null:(prepareOrder.get("service_name").toString()));
				        //32.客服电话
				        order.setServicePhone(prepareOrder.get("service_phone")==null?null:(prepareOrder.get("service_phone").toString()));
				        //33.合同开工时间
				        order.setContractStartDate(prepareOrder.get("contract_start_date").equals("")?null:(DateUtils.parseDate(prepareOrder.get("contract_start_date").toString())));
				        //34.合同竣工时间
				        order.setContractEndDate(prepareOrder.get("contract_end_date").equals("")?null:(DateUtils.parseDate(prepareOrder.get("contract_end_date").toString())));
				        //35.建筑面积
				        order.setCoveredArea(prepareOrder.get("covered_area")==null?null:(prepareOrder.get("covered_area").toString()));
				        //36.合同面积
				        order.setContractArea(prepareOrder.get("contract_area")==null?null:(prepareOrder.get("contract_area").toString()));
				        //37.合同工期
				        order.setContractTime(prepareOrder.get("contract_time").equals("")?null:(Integer.valueOf(prepareOrder.get("contract_time").toString())));
				        //38.签约日期
				        order.setSignContractDate(prepareOrder.get("sign_contract_date").equals("")?null:(DateUtils.parseDate(prepareOrder.get("sign_contract_date").toString())));
				        //39.接单区域
				        order.setBizOrderAcceptArea(prepareOrder.get("biz_order_accept_area")==null?null:(prepareOrder.get("biz_order_accept_area").toString()));
				        //40.相关安装项
				        
				        //41.预备订单状态
				        order.setStatus(ConstantUtils.PREPARE_ORDER_STATUS_10);
				        //42.同步数据ID
				        order.setSynDataId(orderJsonMapping.getDataId());
				        //43.合同金额
				        order.setContractAmount(prepareOrder.get("contract_amount").equals("")?null:(Double.valueOf(prepareOrder.get("contract_amount").toString())));
				        //44.客户级别
				        String customerType = prepareOrder.get("customer_level").equals("")?"":(prepareOrder.get("customer_level").toString());
				        //45.审计员的名称
				        order.setAuditorName(prepareOrder.get("auditor_realname").equals("")?"":(prepareOrder.get("auditor_realname").toString()));
				        //45.审计员的电话
				        order.setAuditorPhone(prepareOrder.get("auditor_mobile").equals("")?"":(prepareOrder.get("auditor_mobile").toString()));
				        //46.串单标签
				        order.setAuditorTagname(prepareOrder.get("tagname").equals("")?"":(prepareOrder.get("tagname").toString()));
				        //根据手机号和名字匹配审计员的ID 审计员员工类型 8
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

							//向biz_syn_data表中保存数据  --- 预备订单拒绝
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
							//该json串有问题 但不再提取解析,更新状态
							orderMappingDao.updateSynDataStatus(orderJsonMapping.getDataId(), ConstantUtils.SYN_STATUS_3);

						}

				    }
				 }
				
				//2:解析并插入预备订单中
				//3:更新syn_data的状态
				
				
		}
		
		 }else{
			 
			 logger.debug("暂无需要接收的数据:  +当前时间:  " +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) );
		 }
				
				
				
			 
			 
			 
		}
}
