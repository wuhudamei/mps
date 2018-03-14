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


	public String receiveJsonData(Map<String, Object> map){

		
		

        String key  = (String) map.get("key");

		String orderNumber  = (String) map.get("orderNumber");

		String changeBillCode  = (String) map.get("changeBillCode");

		String changeApplyDate  = (String) map.get("changeApplyDate");

		String designerName  = (String) map.get("designerName");

		String changeCheckedDate  = (String) map.get("changeCheckedDate");

		String checkerName  = (String) map.get("checkerName");

		String changeReason  = (String) map.get("changeReason");







		



		String[] array = new String[]{"orderNumber:"+orderNumber,"changeBillCode:"+changeBillCode,"changeApplyDate:"+changeApplyDate,"designerName:"+designerName,
				"changeCheckedDate:"+changeCheckedDate,"checkerName:"+checkerName,"changeReason:"+changeReason};
		

		String md5 = KeyAuthenticateUtils.getAndKey(array, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);

		Map<String,String> resultMap = new HashMap<String,String>();

		if(!md5.equals(key)){
			resultMap.put("code", "406");
			resultMap.put("message", "签名认证失败");
			return JSONObject.fromObject(resultMap).toString();


		}else{
			try {
				String result = saveJSONDate(map);

				return result;

			} catch (Exception e) {
				String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
				log.error("==========选材变更接收数据异常!!　时间为: "+date +"　  　选材变更接口接收数据为: " +JSONArray.fromObject(map)+"===========");
				log.error("==========选材变更接收数据异常!!　时间为: "+date +"　  　选材变更接口接收数据为: " +JSONArray.fromObject(map)+"===========");
				log.error("==========选材变更接口异常!! =====  异常时间:"+date+"=====异常为: "+ e);

				resultMap.put("code", "500");
				resultMap.put("message", "保存失败");
				return JSONObject.fromObject(resultMap).toString();
			}
		}
		
		
		
	}



	public String saveJSONDate(Map<String, Object> map) {
		
		

		

		String orderNumber  = (String) map.get("orderNumber");

		String changeBillCode  = (String) map.get("changeBillCode");

		String changeApplyDate  = (String) map.get("changeApplyDate");

		String designerName  = (String) map.get("designerName");

		String changeCheckedDate  = (String) map.get("changeCheckedDate");

		String checkerName  = (String) map.get("checkerName");

		String changeReason  = (String) map.get("changeReason");







		Map<String,String> resultMap = new HashMap<String,String>();


		if(StringUtils.isBlank(orderNumber)){

			resultMap.put("code", "401");
			resultMap.put("message", "必填参数订单编号为空");
			return JSONObject.fromObject(resultMap).toString();
		}
		if(StringUtils.isBlank(changeBillCode)){

			resultMap.put("code", "401");
			resultMap.put("message", "必填参数订单编号为空");
			return JSONObject.fromObject(resultMap).toString();
		}
		

		Object object = map.get("changeItemInfo");
        JSONArray jsonArray = JSONArray.fromObject(object);

        List<Map<String, String>> mapListJson = (List<Map<String, String>>) jsonArray;
        

        if(CollectionUtils.isEmpty(mapListJson)){

			resultMap.put("code", "401");
			resultMap.put("message", "选材变更单材料为空");
			return JSONObject.fromObject(resultMap).toString();
        }
        

        Integer orderId = bizMaterialsChoiceBillDao.findOrderIdMessage(orderNumber);
		
		

        BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill = new BizMaterialsChoiceChangeBill();

        bizMaterialsChoiceChangeBill.setOrderId(orderId);

        bizMaterialsChoiceChangeBill.setOrderNumber(orderNumber);

        bizMaterialsChoiceChangeBill.setChangeBillCode(changeBillCode);

        if(StringUtils.isNotBlank(changeReason)){
        	bizMaterialsChoiceChangeBill.setChangeReason(changeReason);
        }

        if(StringUtils.isNotBlank(changeApplyDate)){
        	bizMaterialsChoiceChangeBill.setChangeApplyDate(DateUtils.parseDate(changeApplyDate));
        }

        if(StringUtils.isNotBlank(designerName)){
        	bizMaterialsChoiceChangeBill.setDesignerName(designerName);
        }

        if(StringUtils.isNotBlank(changeCheckedDate)){
        	bizMaterialsChoiceChangeBill.setChangeCheckedDate(DateUtils.parseDate(changeCheckedDate));
        }

        if(StringUtils.isNotBlank(checkerName)){
        	bizMaterialsChoiceChangeBill.setCheckerName(checkerName);
        }












        
      	bizMaterialsChoiceChangeBill.preInsert();
      	bizMaterialsChoiceChangeBillDao.insert(bizMaterialsChoiceChangeBill);
      	
      	

      	if(null==bizMaterialsChoiceChangeBill.getId()){


			resultMap.put("code", "500");
			resultMap.put("message", "选材变更单保存失败");
			return JSONObject.fromObject(resultMap).toString();
      	}
      	

      	List<BizMaterialsChoiceChangeBillItem>  list = new ArrayList<BizMaterialsChoiceChangeBillItem>();
        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String, String> obj = mapListJson.get(i);
            
            BizMaterialsChoiceChangeBillItem  bizMaterialsChoiceChangeBillItem = new BizMaterialsChoiceChangeBillItem();

            bizMaterialsChoiceChangeBillItem.setMaterialsChoiceChangeBillId(bizMaterialsChoiceChangeBill.getId());

			String changeType  = (String) obj.get("changeType");
			if(StringUtils.isNotBlank(changeType)){
				bizMaterialsChoiceChangeBillItem.setChangeType(changeType);
			}

			String materialsChoiceType  = (String) obj.get("materialsChoiceType");
			if(StringUtils.isNotBlank(materialsChoiceType)){
				bizMaterialsChoiceChangeBillItem.setMaterialsChoiceType(materialsChoiceType);
			}

			String materialsChoiceCategoryCode  = (String) obj.get("materialsChoiceCategoryCode");
			if(StringUtils.isNotBlank(materialsChoiceCategoryCode)){
				bizMaterialsChoiceChangeBillItem.setMaterialsChoiceCategoryCode(materialsChoiceCategoryCode);
			}

			String brand  = (String) obj.get("brand");
			if(StringUtils.isNotBlank(brand)){
				bizMaterialsChoiceChangeBillItem.setBrand(brand);
			}

			String model  = (String) obj.get("model");
			if(StringUtils.isNotBlank(model)){
				bizMaterialsChoiceChangeBillItem.setModel(model);
			}

			String attribute  = (String) obj.get("attribute");
			if(StringUtils.isNotBlank(attribute)){
				bizMaterialsChoiceChangeBillItem.setAttribute(attribute);
			}

			String unit  = (String) obj.get("unit");
			if(StringUtils.isNotBlank(unit)){
				bizMaterialsChoiceChangeBillItem.setUnit(unit);
			}

			String spec  = (String) obj.get("spec");
			if(StringUtils.isNotBlank(spec)){
				bizMaterialsChoiceChangeBillItem.setSpec(spec);
			}

			String changeNumber  = (String) obj.get("changeNumber");
			if(StringUtils.isNotBlank(changeNumber)){
				bizMaterialsChoiceChangeBillItem.setChangeNumber(changeNumber);
			}










			bizMaterialsChoiceChangeBillItem.preInsert();
			list.add(bizMaterialsChoiceChangeBillItem);
			
        }
        

        if(CollectionUtils.isNotEmpty(list)){

        	boolean flag = bizMaterialsChoiceChangeBillItemDao.insertMaterialsChoiceChangeBillItemList(list);

        	if(!flag){

        		bizMaterialsChoiceChangeBillDao.deleteMaterialsChoiceChangeBill(bizMaterialsChoiceChangeBill.getId());

				resultMap.put("code", "500");
				resultMap.put("message", "材料单材料保存失败");
				return JSONObject.fromObject(resultMap).toString();
        	}
      	}
        

		resultMap.put("code", "200");
		resultMap.put("message", "成功");
		return JSONObject.fromObject(resultMap).toString();
	}

	
}
