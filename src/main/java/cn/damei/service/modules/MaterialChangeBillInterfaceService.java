package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.web.modules.MaterialInterfaceController;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.KeyAuthenticateUtils;
import cn.damei.dao.modules.BizMaterialsChoiceBillDao;
import cn.damei.dao.modules.BizMaterialsChoiceChangeBillDao;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBill;
import cn.damei.dao.modules.BizMaterialsChoiceChangeBillItemDao;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBillItem;

import net.sf.json.JSONArray;

@Service
public class MaterialChangeBillInterfaceService {

	@Autowired
	private BizMaterialsChoiceChangeBillDao bizMaterialsChoiceChangeBillDao;
	@Autowired
	private BizMaterialsChoiceChangeBillItemDao bizMaterialsChoiceChangeBillItemDao;
	@Autowired
	private BizMaterialsChoiceBillDao bizMaterialsChoiceBillDao;

	private Logger log = LoggerFactory.getLogger(MaterialInterfaceController.class);

	/**
	 * 变更单接口
	 * @param map
	 * @return
	 */
	public String receiveJsonData(Map<String, Object> map){

		
		
		//1.key
        String key  = (String) map.get("key");
        //2.订单编号
		String orderNumber  = (String) map.get("orderNumber");
		//3.变更单号
		String changeBillCode  = (String) map.get("changeBillCode");
		//4.设计师申请变更日期
		String changeApplyDate  = (String) map.get("changeApplyDate");
		//5.设计师
		String designerName  = (String) map.get("designerName");
		//6.变更单审核通过日期
		String changeCheckedDate  = (String) map.get("changeCheckedDate");
		//7.审计员
		String checkerName  = (String) map.get("checkerName");
		//8.变更原因
		String changeReason  = (String) map.get("changeReason");
//		//9.增项合计
//		String addItemTotalAmount  = (String) map.get("addItemTotalAmount");
//		//10.减项合计
//		String reduceItemTotalAmount  = (String) map.get("reduceItemTotalAmount");
//		//11.变更单总金额
//		String changeBillTotalAmount  = (String) map.get("changeBillTotalAmount");

		
//		String[] array = new String[]{"orderNumber:"+orderNumber,"changeBillCode:"+changeBillCode,"changeApplyDate:"+changeApplyDate,"designerName:"+designerName,
//				"changeCheckedDate:"+changeCheckedDate,"checkerName:"+checkerName,"changeReason:"+changeReason,"addItemTotalAmount:"+addItemTotalAmount,
//				"reduceItemTotalAmount:"+reduceItemTotalAmount,"changeBillTotalAmount:"+changeBillTotalAmount};
		String[] array = new String[]{"orderNumber:"+orderNumber,"changeBillCode:"+changeBillCode,"changeApplyDate:"+changeApplyDate,"designerName:"+designerName,
				"changeCheckedDate:"+changeCheckedDate,"checkerName:"+checkerName,"changeReason:"+changeReason};
		
		//MD5加密
		String md5 = KeyAuthenticateUtils.getAndKey(array, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);

		Map<String,String> resultMap = new HashMap<String,String>();//返回的结果
		//校验加密
		if(!md5.equals(key)){
			resultMap.put("code", "406");
			resultMap.put("message", "签名认证失败");
			return JSONObject.fromObject(resultMap).toString();

//			return "{'code':406,'message':'签名认证失败'}";
		}else{
			try {
				String result = saveJSONDate(map);

				return result;

			} catch (Exception e) {
				String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
				log.error("==========选材变更接收数据异常!!　时间为: "+date +"　  　选材变更接口接收数据为: " +JSONArray.fromObject(map)+"===========");
				log.error("==========选材变更接收数据异常!!　时间为: "+date +"　  　选材变更接口接收数据为: " +JSONArray.fromObject(map)+"===========");
				log.error("==========选材变更接口异常!! =====  异常时间:"+date+"=====异常为: "+ e);
//				return "{'code':500,'message':'保存失败'}";
				resultMap.put("code", "500");
				resultMap.put("message", "保存失败");
				return JSONObject.fromObject(resultMap).toString();
			}
		}
		
		
		
	}


	/**
	 * 保存接收的数据
	 * @param map
	 * @return 
	 */
	public String saveJSONDate(Map<String, Object> map) {
		
		
		//一、选材变更单
		
        //1.订单编号
		String orderNumber  = (String) map.get("orderNumber");
		//2.变更单号
		String changeBillCode  = (String) map.get("changeBillCode");
		//3.设计师申请变更日期
		String changeApplyDate  = (String) map.get("changeApplyDate");
		//4.设计师
		String designerName  = (String) map.get("designerName");
		//5.变更单审核通过日期
		String changeCheckedDate  = (String) map.get("changeCheckedDate");
		//6.审计员
		String checkerName  = (String) map.get("checkerName");
		//7.变更原因
		String changeReason  = (String) map.get("changeReason");
//		//8.增项合计
//		String addItemTotalAmount  = (String) map.get("addItemTotalAmount");
//		//9.减项合计
//		String reduceItemTotalAmount  = (String) map.get("reduceItemTotalAmount");
//		//10.变更单总金额
//		String changeBillTotalAmount  = (String) map.get("changeBillTotalAmount");

		Map<String,String> resultMap = new HashMap<String,String>();

		//二、数据校验
		if(StringUtils.isBlank(orderNumber)){
//			return "{'code':401,'message':'必填参数订单编号为空'}";
			resultMap.put("code", "401");
			resultMap.put("message", "必填参数订单编号为空");
			return JSONObject.fromObject(resultMap).toString();
		}
		if(StringUtils.isBlank(changeBillCode)){
//			return "{'code':401,'message':'必填参数变更单编号为空'}";
			resultMap.put("code", "401");
			resultMap.put("message", "必填参数订单编号为空");
			return JSONObject.fromObject(resultMap).toString();
		}
		
		//三、选材变更单材料表
		Object object = map.get("changeItemInfo");
        JSONArray jsonArray = JSONArray.fromObject(object);

        List<Map<String, String>> mapListJson = (List<Map<String, String>>) jsonArray;
        
        //四、数据校验
        if(CollectionUtils.isEmpty(mapListJson)){
//        	return "{'code':401,'message':'选材变更单材料为空'}";
			resultMap.put("code", "401");
			resultMap.put("message", "选材变更单材料为空");
			return JSONObject.fromObject(resultMap).toString();
        }
        
        //五、查询订单id
        Integer orderId = bizMaterialsChoiceBillDao.findOrderIdMessage(orderNumber);
		
		
		//六、保存选材变更单
        BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill = new BizMaterialsChoiceChangeBill();
        //1.订单id
        bizMaterialsChoiceChangeBill.setOrderId(orderId);
        //2.订单编号
        bizMaterialsChoiceChangeBill.setOrderNumber(orderNumber);
        //3.变更单号
        bizMaterialsChoiceChangeBill.setChangeBillCode(changeBillCode);
        //4.变更原因
        if(StringUtils.isNotBlank(changeReason)){
        	bizMaterialsChoiceChangeBill.setChangeReason(changeReason);
        }
        //5.设计师申请变更日期
        if(StringUtils.isNotBlank(changeApplyDate)){
        	bizMaterialsChoiceChangeBill.setChangeApplyDate(DateUtils.parseDate(changeApplyDate));
        }
        //6.设计师
        if(StringUtils.isNotBlank(designerName)){
        	bizMaterialsChoiceChangeBill.setDesignerName(designerName);
        }
        //7.变更单审核通过日期
        if(StringUtils.isNotBlank(changeCheckedDate)){
        	bizMaterialsChoiceChangeBill.setChangeCheckedDate(DateUtils.parseDate(changeCheckedDate));
        }
        //8.审计员
        if(StringUtils.isNotBlank(checkerName)){
        	bizMaterialsChoiceChangeBill.setCheckerName(checkerName);
        }
//        //9.增项合计
//        if(StringUtils.isNotBlank(addItemTotalAmount)){
//        	bizMaterialsChoiceChangeBill.setAddItemTotalAmount(Double.valueOf(addItemTotalAmount));
//        }
//        //10.减项合计
//        if(StringUtils.isNotBlank(reduceItemTotalAmount)){
//        	bizMaterialsChoiceChangeBill.setReduceItemTotalAmount(Double.valueOf(reduceItemTotalAmount));
//        }
//        //11.变更单总金额
//        if(StringUtils.isNotBlank(changeBillTotalAmount)){
//        	bizMaterialsChoiceChangeBill.setChangeBillTotalAmount(Double.valueOf(changeBillTotalAmount));
//        }
        
      	bizMaterialsChoiceChangeBill.preInsert();
      	bizMaterialsChoiceChangeBillDao.insert(bizMaterialsChoiceChangeBill);
      	
      	
      	//七、判断选材变更单是否保存成功
      	if(null==bizMaterialsChoiceChangeBill.getId()){
      		//7.1 保存失败
//      		return "{'code':500,'message':'选材变更单保存失败'}";
			resultMap.put("code", "500");
			resultMap.put("message", "选材变更单保存失败");
			return JSONObject.fromObject(resultMap).toString();
      	}
      	
      	//八、保存选材变更单材料--集合中
      	List<BizMaterialsChoiceChangeBillItem>  list = new ArrayList<BizMaterialsChoiceChangeBillItem>();
        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String, String> obj = mapListJson.get(i);
            
            BizMaterialsChoiceChangeBillItem  bizMaterialsChoiceChangeBillItem = new BizMaterialsChoiceChangeBillItem();
            // 1.变更单id
            bizMaterialsChoiceChangeBillItem.setMaterialsChoiceChangeBillId(bizMaterialsChoiceChangeBill.getId());
			//2.变更类型
			String changeType  = (String) obj.get("changeType");
			if(StringUtils.isNotBlank(changeType)){
				bizMaterialsChoiceChangeBillItem.setChangeType(changeType);
			}
			//3.选材类型
			String materialsChoiceType  = (String) obj.get("materialsChoiceType");
			if(StringUtils.isNotBlank(materialsChoiceType)){
				bizMaterialsChoiceChangeBillItem.setMaterialsChoiceType(materialsChoiceType);
			}
			//4.材料类目编码
			String materialsChoiceCategoryCode  = (String) obj.get("materialsChoiceCategoryCode");
			if(StringUtils.isNotBlank(materialsChoiceCategoryCode)){
				bizMaterialsChoiceChangeBillItem.setMaterialsChoiceCategoryCode(materialsChoiceCategoryCode);
			}
			//5.品牌
			String brand  = (String) obj.get("brand");
			if(StringUtils.isNotBlank(brand)){
				bizMaterialsChoiceChangeBillItem.setBrand(brand);
			}
			//6.型号
			String model  = (String) obj.get("model");
			if(StringUtils.isNotBlank(model)){
				bizMaterialsChoiceChangeBillItem.setModel(model);
			}
			//7.属性
			String attribute  = (String) obj.get("attribute");
			if(StringUtils.isNotBlank(attribute)){
				bizMaterialsChoiceChangeBillItem.setAttribute(attribute);
			}
			//8.单位
			String unit  = (String) obj.get("unit");
			if(StringUtils.isNotBlank(unit)){
				bizMaterialsChoiceChangeBillItem.setUnit(unit);
			}
			//9.规格
			String spec  = (String) obj.get("spec");
			if(StringUtils.isNotBlank(spec)){
				bizMaterialsChoiceChangeBillItem.setSpec(spec);
			}
			//10.变更用量
			String changeNumber  = (String) obj.get("changeNumber");
			if(StringUtils.isNotBlank(changeNumber)){
				bizMaterialsChoiceChangeBillItem.setChangeNumber(changeNumber);
			}
//			//11.单价
//			String unitPrice  = (String) obj.get("unitPrice");
//			if(StringUtils.isNotBlank(unitPrice)){
//				bizMaterialsChoiceChangeBillItem.setUnitPrice(Double.valueOf(unitPrice));
//			}
//			//12.变更合计金额
//			String totalAmount  = (String) obj.get("totalAmount");
//			if(StringUtils.isNotBlank(totalAmount)){
//				bizMaterialsChoiceChangeBillItem.setTotalAmount(Double.valueOf(totalAmount));
//			}
			bizMaterialsChoiceChangeBillItem.preInsert();
			list.add(bizMaterialsChoiceChangeBillItem);
			
        }
        
        //九、如果集合不为空--保存选材变更单材料
        if(CollectionUtils.isNotEmpty(list)){
      		//9.1批量保存选材变更单材料
        	boolean flag = bizMaterialsChoiceChangeBillItemDao.insertMaterialsChoiceChangeBillItemList(list);
        	//9.2如果保存失败
        	if(!flag){
        		//9.3删除选材变更单
        		bizMaterialsChoiceChangeBillDao.deleteMaterialsChoiceChangeBill(bizMaterialsChoiceChangeBill.getId());
//        		return "{'code':500,'message':'材料单材料保存失败'}";
				resultMap.put("code", "500");
				resultMap.put("message", "材料单材料保存失败");
				return JSONObject.fromObject(resultMap).toString();
        	}
      	}
        
//        return "{'code':200,'message':'成功'}";
		resultMap.put("code", "200");
		resultMap.put("message", "成功");
		return JSONObject.fromObject(resultMap).toString();
	}

	
}
