package cn.damei.service.modules;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.damei.common.config.Global;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.common.MD5Utils;
import cn.damei.service.mobile.Manager.OrderInstallPlanService;
import cn.damei.dao.modules.BizSynDataDao;
import cn.damei.entity.modules.BizSynData;
import cn.damei.dao.modules.BizSynDateSendAndReceiveDao;
import cn.damei.entity.modules.BizSynDataCnfg;
import cn.damei.entity.modules.ReciveJson;
import cn.damei.dao.modules.ProjectIntemDao;
import cn.damei.entity.modules.ProjectIntem;
import cn.damei.entity.modules.ProjectItemPrice;
import cn.damei.entity.modules.ProjectItemType;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service
public class BizSynDateSendAndReceiveService {

	@Autowired
	private BizSynDateSendAndReceiveDao bd;
	@Autowired
	private OrderInstallPlanService orderInstallPlanService;
	@Autowired
	private BizSynDataDao bizSynDataDao;
	
	@Autowired
	private ProjectIntemDao projectIntemDao;
	
	public void saveJSONDate(String string, String businessType, Date date) {

		bd.saveJSONDate(string,businessType,date);
	}
	public void saveData(Map map) {

		bd.saveData(map);
	}
	public List<BizSynDataCnfg> findSendJsonDate() {

		return bd.findSendJsonDate();
	}

	public void updateBizSynDate(String string2, Date date, String string22) {

		bd.updateBizSynDate(string2,date,string22);
	}

	public void updateBizSynDateStatus(String id) {

		bd.updateBizSynDateStatus(id);
		
	}

	public String findBizSynDateConf(String businessType) {

		return bd.findBizSynDateConf(businessType);
	}

	public String receiveJsonData(ReciveJson reciveJson) throws UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		 Logger logger = Logger.getLogger("logger");
	     logger.error("推送时间：【"+sdf.format(new Date())+"】订单编号：【"+reciveJson.getOrder_number()+"】接口类型：【"+reciveJson.getType()+"】");
		

		String key = Global.getMD5key();

		String businessType = ConstantUtils.BUSINESS_TYPE_101;
		String type = reciveJson.getType();
		if(type!=""&&type!=null){
			businessType = type;
		}

		String autographKey = reciveJson.getKey();
		reciveJson.setKey("");

		JSONArray fromObject = JSONArray.fromObject(reciveJson);
		String string = fromObject.toString();
		

		String substring2 = string.substring(2, string.length()-2);

		String[] split = substring2.split(",\"");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String replaceFirst = split[i].replaceFirst("\"", "");
			list.add(replaceFirst);
		}
		String[] targetArr=new String[list.size()];
		String[] array = list.toArray(targetArr);

		Arrays.sort(array);

		StringBuffer count = new StringBuffer("");
		for (String sp : array) {

			int lastIndexOf = sp.lastIndexOf(':') +2;
			String substring = sp.substring(lastIndexOf, sp.length()-1);
			if(!substring.equals("")&&substring!=null){

				count.append(substring).append("&");
			}
		}
		count.append(key);

		String md5 = MD5Utils.getMD5(count.toString()).toUpperCase();

		Map map = new HashMap();
		if(md5.equals(autographKey)) try {
            saveJSONDate(string, businessType, new Date());
            map.put("code", "200");
            map.put("message", "成功");
            return JSONObject.fromObject(map).toString();

        } catch (Exception e) {
            logger.error(e);
            map.put("code", "500");
            map.put("message", "保存失败");
            return JSONObject.fromObject(map).toString();

        }
        else{
            map.put("code", "406");
            map.put("message", "签名认证失败");
            return JSONObject.fromObject(map).toString();

		}
	}
	public String receiveJsonStr(String reciveJson)  {
		Map<String,String> resultMap = new HashMap<String,String>();
		if(null==reciveJson){
			resultMap.put("code", "310");
			resultMap.put("message", "参数为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}

		reciveJson = reciveJson.replaceAll("[\\x{10000}-\\x{10FFFF}]", "");
		try{
			JSONObject.fromObject(reciveJson);    
		}catch(JSONException e){
			resultMap.put("code", "160");
			resultMap.put("message", "所传参数为非法的json格式！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}

		String key = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";
		Map<String, Object> map;
		
		map = parseJSON2Map(reciveJson);
		if(map.get("key")==null){
			resultMap.put("code", "100");
			resultMap.put("message", "key值为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		if(map.get("businessType")==null){
			resultMap.put("code", "110");
			resultMap.put("message", "businessType值为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		if(map.get("businessOnlyMarkInt")==null){
			resultMap.put("code", "120");
			resultMap.put("message", "businessOnlyMarkInt值为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		if(map.get("businessData")==null){
			resultMap.put("code", "130");
			resultMap.put("message", "businessData值为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		if(map.get("status")==null){
			resultMap.put("code", "180");
			resultMap.put("message", "status值为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		String[] strArr = getStrArr(map);
		String key2 = KeyAuthenticateUtils2.getKey(strArr,key);
		

		String autographKey = map.get("key").toString();
		if(key2.equals(autographKey)){

			String businessType =  map.get("businessType").toString();
			String businessOnlyMarkInt = map.get("businessOnlyMarkInt").toString();

			String businessData = map.get("businessData").toString().replaceAll("[\\x{10000}-\\x{10FFFF}]", "");
			map.put("businessData", businessData);
			try{
				JSONObject.fromObject(businessData);    
			}catch(JSONException e){
				resultMap.put("code", "170");
				resultMap.put("message", "businessData值为非法的json格式！");
				JSONObject jsonObject=JSONObject.fromObject(resultMap);
				return jsonObject.toString();
			}

			int count = bd.selectCountBizSynDataByTypeAndMarkInt(businessType, businessOnlyMarkInt);
			if(count==0){
				saveData(map);
			}else{
				bd.updateData(map);
			}

			if(businessType.equals(ConstantUtils.BUSINESS_TYPE_601)){

				Map<String, Object> m = tranferMapToListMap(parseJSON2Map(businessData));
				if(m.get("workOrderCode")==null){
					resultMap.put("code", "140");
					resultMap.put("message", "workOrderCode值为空！");
					JSONObject jsonObject=JSONObject.fromObject(resultMap);
					return jsonObject.toString();
				}
				String workOrderCode = m.get("workOrderCode").toString();
				m.put("status", map.get("status").toString());

				int proCount = bd.selectCountProblemByTypeAndMarkInt(workOrderCode);
				if(proCount==0){

					bd.saveCustomerServiceDataByMap(m);
				}else{

					bd.updateCustomerServiceDataByMap(m);
				}

				if(null==m.get("liableDepartment")){
					resultMap.put("code", "190");
					resultMap.put("message", "liableDepartment值为空！");
					JSONObject jsonObject=JSONObject.fromObject(resultMap);
					return jsonObject.toString();
				}
				String liableDepartment = m.get("liableDepartment").toString().replaceAll("[\\x{10000}-\\x{10FFFF}]", "");
				try{
					JSONObject.fromObject(liableDepartment);    
				}catch(JSONException e){
					resultMap.put("code", "300");
					resultMap.put("message", "liableDepartment值为非法的json格式！");
					JSONObject jsonObject=JSONObject.fromObject(resultMap);
					return jsonObject.toString();
				}
				Map<String, Object> maplLiableType = parseJSON2Map(liableDepartment);
				maplLiableType.put("familyCode", maplLiableType.get("familyCode").toString().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
				String liableTypeCode = maplLiableType.get("id").toString();
				int liableTypeCount = bd.selectliableTypeCountByMap(liableTypeCode);
				if(liableTypeCount==0){
					bd.insertLiableTypeByMap(maplLiableType);
				}else{
					bd.updateLiableTypeByMap(maplLiableType);
				}
				resultMap.put("code", "200");
				resultMap.put("message", "接收信息成功！");
				return JSONObject.fromObject(resultMap).toString();
			}
		}else{
			resultMap.put("code", "150");
			resultMap.put("message", "key值不匹配！");
			return JSONObject.fromObject(resultMap).toString();
		}
		return JSONObject.fromObject(resultMap).toString();
	}
	public Map<String, Object> tranferMapToListMap(Map<String, Object> objectMap ) {
		Map<String, Object> mapInfo = new HashMap<String, Object>();
        if (null != objectMap) {


            Object companyName = objectMap.get("liableCompanyName");

            if (companyName==null) {
                companyName = "";

            }
            Object workOrderCode = objectMap.get("workOrderCode");


            Object customerName = objectMap.get("customerName");
            Object customerMobile = objectMap.get("customerMobile");

            if (customerMobile!=null) {
                String a = (String) customerMobile;
                if (a.length() != 11) {

                    customerMobile = null;

                }

            }

            Object customerAddress = objectMap.get("customerAddress")==null ? "" : objectMap.get("customerAddress");
            ;









            Object problemCreateDate = objectMap.get("createDate")==null ? "" : objectMap.get("createDate");
            Object problem = objectMap.get("problem")==null ? "" : objectMap.get("problem");


            Object orderNo = objectMap.get("orderNo")==null ? "" : objectMap.get("orderNo");


            Object departMentObject = objectMap.get("liableDepartment");
            Object departId = null;
            if (departMentObject!=null) {
                Map<String, Object> departMap = (Map) departMentObject;
                departId = String.valueOf(departMap.get("id"));
            }
            





















            Object importantDegree1Object = objectMap.get("importantDegree1");


            Object importantDegree1Id = null;

            if (importantDegree1Object!=null) {
                Map<String, Object> importantDegree1Map = (Map) importantDegree1Object;
                importantDegree1Id = importantDegree1Map.get("id");

            }












            Object complaintTypeObject = objectMap.get("complaintType");
            Object  complaintTypeName =null;
            if (null!=complaintTypeObject) {

                Map<String, Object> complaintTypeObjectMap = (Map) complaintTypeObject;
                complaintTypeName = String.valueOf(complaintTypeObjectMap.get("name"));
            }




            Object questionType1Object = objectMap.get("questionType1");
            String questionType1ObjectMapName = null;
            if (null != questionType1Object) {
                Map<String, Object> questionType1ObjectMap = (Map) questionType1Object;
                questionType1ObjectMapName = (String) questionType1ObjectMap.get("name");
            }


            Object questionType2Object = objectMap.get("questionType2");

            String questionType2ObjectMapName = null;
            if (null != questionType1Object) {

                Map<String, Object> questionType2ObjectMap = (Map) questionType2Object;

                questionType2ObjectMapName = (String) questionType2ObjectMap.get("name");



            }

            Object sourceObject = objectMap.get("source");
            String sourceObjectName = null;
            if (null != sourceObject) {

                Map<String, Object> sourceObjectMap = (Map) sourceObject;

                sourceObjectName = (String) sourceObjectMap.get("name");

            }


            Object photo = "";
            try {

                photo = objectMap.get("photo");
            } catch (Exception e) {

                e.printStackTrace();


            }

            String serverUrl = "http://cm.mdni.net.cn";
            if (null != photo && !"".equals(photo.toString().trim())) {

                String photos[] = photo.toString().split(",");

                if (photos.length == 1) {

                    photo = serverUrl + photos[0];

                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int v = 0; v < photos.length; v++) {

                        if(v==photos.length-1){

                            sb.append(serverUrl + photos[v]);
                        }else{

                            sb.append(serverUrl + photos[v]+",");
                        }


                    }
                    photo = sb.toString();


                }


            }

            mapInfo.put("companyName", companyName);
            mapInfo.put("workOrderCode", workOrderCode);
            mapInfo.put("customerName", customerName);
            mapInfo.put("customerMobile", customerMobile);
            mapInfo.put("liableDepartment", departMentObject);

            mapInfo.put("customerAddress", customerAddress);








            mapInfo.put("problemCreateDate", problemCreateDate);
            mapInfo.put("problem", problem);

            
            mapInfo.put("departId", departId);











            mapInfo.put("importantDegree1Id", importantDegree1Id);
            mapInfo.put("complaintTypeName", complaintTypeName);


            mapInfo.put("questionType1ObjectMapName", questionType1ObjectMapName);
            mapInfo.put("questionType2ObjectMapName", questionType2ObjectMapName);
            mapInfo.put("sourceObjectName", sourceObjectName);
            mapInfo.put("orderNo", orderNo);




            mapInfo.put("photo", photo);

        }

        return mapInfo;

    }
	public  String[] getStrArr(Map map){
		String[] strArr = new String[3];
		strArr[0] = "businessType="+map.get("businessType").toString();
		strArr[1] = "businessOnlyMarkInt="+map.get("businessOnlyMarkInt").toString();
		strArr[2] = "businessData="+map.get("businessData").toString();
		return strArr;
	}

	public  Map<String, Object> parseJSON2Map(String jsonStr){    
        Map<String, Object> map = new HashMap<String, Object>();    
        JSONObject json = JSONObject.fromObject(jsonStr);    
        for(Object k : json.keySet()){    
            Object v = json.get(k);     
            if(v instanceof JSONArray){    
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();    
                Iterator<JSONObject> it = ((JSONArray)v).iterator();    
                while(it.hasNext()){    
                    JSONObject json2 = it.next();    
                    list.add(parseJSON2Map(json2.toString()));    
                }    
                map.put(k.toString(), list);    
            } else {    
                map.put(k.toString(), v);    
            }    
        }    
        return map;    
    }  
	
	
	public List<String> findJson() {

		 List<String> list = bd.findJson();
		return list;
	}
	

	public boolean insertBizSynData(String type,String dataDirection,String status){
		try {
			Map<String,String> map = new HashMap<>();

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","id","updateMan","dbName","isNewRecord","createDate","projectItemTypeId","delFlag"});
			List<ProjectItemType> findProjectItemTypeList = projectIntemDao.findProjectItemTypeList();
			JSONArray projectItemTypeList =JSONArray.fromObject(findProjectItemTypeList, jsonConfig);
			map.put("projectIntemType", projectItemTypeList.toString());
			

			List<ProjectIntem> findAllList = projectIntemDao.findList(new ProjectIntem());
			jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","delFlag","projectType","projectItemId","projectIntemTypeName"});
			JSONArray projectIntem =JSONArray.fromObject(findAllList, jsonConfig);
			map.put("projectIntem", projectIntem.toString());
			

			jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","delFlag","itemPriceId","projectIntemName","valuationMethod","projectIntemCode"});
			List<ProjectItemPrice> projectItemPriceList= projectIntemDao.findProjectItemPrice();
			JSONArray projectItemPrice =JSONArray.fromObject(projectItemPriceList, jsonConfig);
			map.put("projectIntemPrice", projectItemPrice.toString());


			String key = KeyAuthenticateUtils2.getKey(map);
			JSONObject fromObject = new JSONObject();
			fromObject.put("projectIntemType", projectItemTypeList.toString());
			fromObject.put("projectIntem", projectIntem.toString());
			fromObject.put("projectIntemPrice", projectItemPrice.toString());
			fromObject.put("key", key);
	        BizSynData bizSynData = new BizSynData();
	        bizSynData.setDataDirection(dataDirection);
	        bizSynData.setBusinessType(type);
	        bizSynData.setBusinessData(fromObject.toString());
	        bizSynData.setSynStatus(status);
	        bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
	        bizSynData.preInsert();
	        bizSynDataDao.insert(bizSynData);
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendProjectItemType(ProjectItemType projectItemType){

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","projectItemTypeId","delFlag"});

		JSONObject projectItemTypeList =JSONObject.fromObject(projectItemType, jsonConfig);

		Map<String,String> map = (Map<String,String>)projectItemTypeList;
		String key = KeyAuthenticateUtils2.getKey(map);
		projectItemTypeList.put("key",key);
		boolean boo = insertBizSynData(ConstantUtils.BUSINESS_TYPE_701, ConstantUtils.DATA_DIRECTION_2, ConstantUtils.SYN_STATUS_4, projectItemTypeList.toString());
		return boo;
	}



	public boolean sendProjectItem(ProjectIntem projectItem){

		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","delFlag","projectType","projectItemId","projectIntemTypeName"});
		JSONObject projectIntem =JSONObject.fromObject(projectItem, jsonConfig);
		JSONArray jsonArray = JSONArray.fromObject(projectIntem);



		Collection collection = JSONArray.toCollection(jsonArray);

		Map<String,String> map = (Map<String,String>)projectIntem;
		String key = KeyAuthenticateUtils2.getKey(map);
		projectIntem.put("key",key);
		boolean boo = insertBizSynData(ConstantUtils.BUSINESS_TYPE_702, ConstantUtils.DATA_DIRECTION_2, ConstantUtils.SYN_STATUS_4, projectIntem.toString());
		return boo;
	}

	public boolean sendProjectItemPrice(ProjectItemPrice projectItemPrice){

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","delFlag","itemPriceId","projectIntemName","valuationMethod","projectIntemCode"});
		JSONObject projectItemPricejson =JSONObject.fromObject(projectItemPrice, jsonConfig);
		Map<String,String> map = (Map<String,String>)projectItemPricejson;
		String key = KeyAuthenticateUtils2.getKey(map);
		projectItemPricejson.put("effectDate",projectItemPrice.getEffectDateString());
		projectItemPricejson.remove("effectDateString");
		JSONArray jsonArray = JSONArray.fromObject(projectItemPricejson);
		projectItemPricejson.put("key",key);
		boolean boo = insertBizSynData(ConstantUtils.BUSINESS_TYPE_703, ConstantUtils.DATA_DIRECTION_2, ConstantUtils.SYN_STATUS_4, projectItemPricejson.toString());
		return boo;
	}


	public boolean insertBizSynData(String type,String dataDirection,String status,String fromObject){
		try {
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(dataDirection);
			bizSynData.setBusinessType(type);
			bizSynData.setBusinessData(fromObject);
			bizSynData.setSynStatus(status);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataDao.insert(bizSynData);
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
