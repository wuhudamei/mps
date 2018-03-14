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
		// TODO Auto-generated method stub
		bd.saveJSONDate(string,businessType,date);
	}
	public void saveData(Map map) {
		// TODO Auto-generated method stub
		bd.saveData(map);
	}
	public List<BizSynDataCnfg> findSendJsonDate() {
		// TODO Auto-generated method stub
		return bd.findSendJsonDate();
	}

	public void updateBizSynDate(String string2, Date date, String string22) {
		// TODO Auto-generated method stub
		bd.updateBizSynDate(string2,date,string22);
	}

	public void updateBizSynDateStatus(String id) {
		// TODO Auto-generated method stub
		bd.updateBizSynDateStatus(id);
		
	}

	public String findBizSynDateConf(String businessType) {
		// TODO Auto-generated method stub
		return bd.findBizSynDateConf(businessType);
	}

	public String receiveJsonData(ReciveJson reciveJson) throws UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//日志记录 
		 Logger logger = Logger.getLogger("logger");
	     logger.error("推送时间：【"+sdf.format(new Date())+"】订单编号：【"+reciveJson.getOrder_number()+"】接口类型：【"+reciveJson.getType()+"】");
		
		//双反约定的key
		String key = Global.getMD5key();		//获取业务类型
		//String businessType = reciveJson.getBusiness_type();
		String businessType = ConstantUtils.BUSINESS_TYPE_101;
		String type = reciveJson.getType();
		if(type!=""&&type!=null){
			businessType = type;
		}
		//获取签名串
		String autographKey = reciveJson.getKey();
		reciveJson.setKey("");
		//把对象转成json字符串
		JSONArray fromObject = JSONArray.fromObject(reciveJson);
		String string = fromObject.toString();
		
		//把截取字符串
		String substring2 = string.substring(2, string.length()-2);
		//把字符转换成list数组	
		String[] split = substring2.split(",\"");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String replaceFirst = split[i].replaceFirst("\"", "");
			list.add(replaceFirst);
		}
		String[] targetArr=new String[list.size()];
		String[] array = list.toArray(targetArr);
		//对数组进行排序
		Arrays.sort(array);
		//遍历数组
		StringBuffer count = new StringBuffer("");
		for (String sp : array) {
			//截取字符串
			int lastIndexOf = sp.lastIndexOf(':') +2;
			String substring = sp.substring(lastIndexOf, sp.length()-1);
			if(!substring.equals("")&&substring!=null){
				//count = count+substring+"&";
				count.append(substring).append("&");
			}
		}
		count.append(key);
		//MD5加密
		String md5 = MD5Utils.getMD5(count.toString()).toUpperCase();
		//校验加密
		Map map = new HashMap();
		if(md5.equals(autographKey)) try {
            saveJSONDate(string, businessType, new Date());
            map.put("code", "200");
            map.put("message", "成功");
            return JSONObject.fromObject(map).toString();
            /*return "{'code':200,'message':'成功'}";*/
        } catch (Exception e) {
            logger.error(e);
            map.put("code", "500");
            map.put("message", "保存失败");
            return JSONObject.fromObject(map).toString();
          /*  return "{'code':500,'message':'保存失败'}";*/
        }
        else{
            map.put("code", "406");
            map.put("message", "签名认证失败");
            return JSONObject.fromObject(map).toString();
		/*	return "{'code':406,'message':'签名认证失败'}";*/
		}
	}
	public String receiveJsonStr(String reciveJson)  {
		Map<String,String> resultMap = new HashMap<String,String>();//返回的结果
		if(null==reciveJson){
			resultMap.put("code", "310");
			resultMap.put("message", "参数为空！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		//暴力去掉特殊的表情符
		reciveJson = reciveJson.replaceAll("[\\x{10000}-\\x{10FFFF}]", "");
		try{
			JSONObject.fromObject(reciveJson);    
		}catch(JSONException e){
			resultMap.put("code", "160");
			resultMap.put("message", "所传参数为非法的json格式！");
			JSONObject jsonObject=JSONObject.fromObject(resultMap);
			return jsonObject.toString();
		}
		//双方约定的key
		String key = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";		//获取业务类型
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
		
		//获取签名串
		String autographKey = map.get("key").toString();
		if(key2.equals(autographKey)){
			//存进同步表
			String businessType =  map.get("businessType").toString();
			String businessOnlyMarkInt = map.get("businessOnlyMarkInt").toString();
			//暴力去掉特殊的表情符
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
			//查询同步表是否插入过数据，如果没有插入过就插入，否则修改
			int count = bd.selectCountBizSynDataByTypeAndMarkInt(businessType, businessOnlyMarkInt);
			if(count==0){
				saveData(map);
			}else{
				bd.updateData(map);
			}
			//如果是工程投诉-投诉信息
			if(businessType.equals(ConstantUtils.BUSINESS_TYPE_601)){
				//得到需要的数据
				Map<String, Object> m = tranferMapToListMap(parseJSON2Map(businessData));
				if(m.get("workOrderCode")==null){
					resultMap.put("code", "140");
					resultMap.put("message", "workOrderCode值为空！");
					JSONObject jsonObject=JSONObject.fromObject(resultMap);
					return jsonObject.toString();
				}
				String workOrderCode = m.get("workOrderCode").toString();
				m.put("status", map.get("status").toString());
				//查询problem表里是否已经有数据
				int proCount = bd.selectCountProblemByTypeAndMarkInt(workOrderCode);
				if(proCount==0){
					//插入售后问题表
					bd.saveCustomerServiceDataByMap(m);
				}else{
					//更改信息
					bd.updateCustomerServiceDataByMap(m);
				}
				///以下代码是为了插入或者更新表biz_cus_service_liable_type，如果不存在插入，存在就更新
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

            //Object contractorName = objectMap.get("contractorName").equals(null) ? "" : objectMap.get("contractorName");
            //Object contractorMobile = objectMap.get("contractorMobile").equals(null) ? "" : objectMap.get("contractorMobile");
            //
            //
            //Object supervisorName = objectMap.get("supervisorName").equals(null) ? "" : objectMap.get("supervisorName");
            //Object supervisorMobile = objectMap.get("supervisorMobile").equals(null) ? "" : objectMap.get("supervisorMobile");


            Object problemCreateDate = objectMap.get("createDate")==null ? "" : objectMap.get("createDate");
            Object problem = objectMap.get("problem")==null ? "" : objectMap.get("problem");


            Object orderNo = objectMap.get("orderNo")==null ? "" : objectMap.get("orderNo");


            Object departMentObject = objectMap.get("liableDepartment");
            Object departId = null;
            if (departMentObject!=null) {
                Map<String, Object> departMap = (Map) departMentObject;
                departId = String.valueOf(departMap.get("id"));
            }
            

            //Object liableType1Object = objectMap.get("liableType1");
            //Object liableTypeId = null;
            //if (!liableType1Object.equals(null)) {
            //
            //    Map<String, Object> liableType1Map = (Map) liableType1Object;
            //    liableTypeId = String.valueOf(liableType1Map.get("id"));
            //}





            //
            //Object liableType2Object = objectMap.get("liableType2");
            //
            //Map<String, Object> liableType2Map = (Map) liableType2Object;
            //Object liableType2Id = String.valueOf((Integer) liableType2Map.get("id"));
            //String liableType2Name = (String) liableType2Map.get("name");


            Object importantDegree1Object = objectMap.get("importantDegree1");


            Object importantDegree1Id = null;

            if (importantDegree1Object!=null) {
                Map<String, Object> importantDegree1Map = (Map) importantDegree1Object;
                importantDegree1Id = importantDegree1Map.get("id");

            }


            //Object importantDegree2Object = objectMap.get("importantDegree2");
            //
            //Map<String, Object> importantDegree2Map = (Map) importantDegree2Object;
            //Object importantDegree2Id =  importantDegree2Map.get("id");
            //String importantDegree2Name = (String) importantDegree2Map.get("name");


            /**
             * 2017-07-25 接口调整添加字段
             */


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

            //mapInfo.put("contractorName", contractorName);
            //mapInfo.put("contractorMobile", contractorMobile);
            //
            //mapInfo.put("supervisorName", supervisorName);
            //mapInfo.put("supervisorMobile", supervisorMobile);


            mapInfo.put("problemCreateDate", problemCreateDate);
            mapInfo.put("problem", problem);

            
            mapInfo.put("departId", departId);
//                mapInfo.put("departName", departName);


            //mapInfo.put("liableTypeId", liableTypeId);
//                mapInfo.put("liableTypeName", liableTypeName);


            //mapInfo.put("liableType2Id", liableType2Id);
//                mapInfo.put("liableType2Name", liableType2Name);


            mapInfo.put("importantDegree1Id", importantDegree1Id);
            mapInfo.put("complaintTypeName", complaintTypeName);
//                mapInfo.put("importantDegree1Name", importantDegree1Name);

            mapInfo.put("questionType1ObjectMapName", questionType1ObjectMapName);
            mapInfo.put("questionType2ObjectMapName", questionType2ObjectMapName);
            mapInfo.put("sourceObjectName", sourceObjectName);
            mapInfo.put("orderNo", orderNo);


            //mapInfo.put("importantDegree2Id", importantDegree2Id);
//                mapInfo.put("importantDegree2Name", importantDegree2Name);
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
	/**
	 * 
	 * @param
	 * @return
	 */
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
		// TODO Auto-generated method stub
		 List<String> list = bd.findJson();
		return list;
	}
	
	/** 
	* @Description: 插入数据到同步数据表
	* @param @param type 接口的类型
	* @param @param dataDirection 数据的走向 
	* @param @param status 同步的状态
	* @author zkj 
	* @date 2017年11月3日 下午3:11:35 
	*/
	public boolean insertBizSynData(String type,String dataDirection,String status){
		try {
			Map<String,String> map = new HashMap<>();
//			查询所有的施工项分类
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","id","updateMan","dbName","isNewRecord","createDate","projectItemTypeId","delFlag"});
			List<ProjectItemType> findProjectItemTypeList = projectIntemDao.findProjectItemTypeList();
			JSONArray projectItemTypeList =JSONArray.fromObject(findProjectItemTypeList, jsonConfig);
			map.put("projectIntemType", projectItemTypeList.toString());
			
//			查询所有的是施工项
			List<ProjectIntem> findAllList = projectIntemDao.findList(new ProjectIntem());
			jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","delFlag","projectType","projectItemId","projectIntemTypeName"});
			JSONArray projectIntem =JSONArray.fromObject(findAllList, jsonConfig);
			map.put("projectIntem", projectIntem.toString());
			
//			查询所有的价格
			jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","delFlag","itemPriceId","projectIntemName","valuationMethod","projectIntemCode"});
			List<ProjectItemPrice> projectItemPriceList= projectIntemDao.findProjectItemPrice();
			JSONArray projectItemPrice =JSONArray.fromObject(projectItemPriceList, jsonConfig);
			map.put("projectIntemPrice", projectItemPrice.toString());
//			格式化json
//			对数据进行加密 获得加密密文
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
	/**
	* @Description: 推送施工变更项分类
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/7 16:30
	*/
	public boolean sendProjectItemType(ProjectItemType projectItemType){
//		查询所有的施工项分类
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"createBy","updateBy","currentUser","page","sqlMap","currentUser","frontSort","global","remarks","updateDate","updateMan","dbName","isNewRecord","createDate","projectItemTypeId","delFlag"});
//		List<ProjectItemType> findProjectItemTypeList = projectIntemDao.findProjectItemTypeList();
		JSONObject projectItemTypeList =JSONObject.fromObject(projectItemType, jsonConfig);
//		map.put("projectIntemType", projectItemTypeList.toString());
		Map<String,String> map = (Map<String,String>)projectItemTypeList;
		String key = KeyAuthenticateUtils2.getKey(map);
		projectItemTypeList.put("key",key);
		boolean boo = insertBizSynData(ConstantUtils.BUSINESS_TYPE_701, ConstantUtils.DATA_DIRECTION_2, ConstantUtils.SYN_STATUS_4, projectItemTypeList.toString());
		return boo;
	}


	/**
	* @Description: 推送施工变更项
	* @Author zhangkangjian
	* @param
	* @param projectItem
	 * @return
	* @Date 2017/11/7 16:55
	*/
	public boolean sendProjectItem(ProjectIntem projectItem){
//		查询所有的是施工项
		JsonConfig jsonConfig = new JsonConfig();
//		List<ProjectIntem> findAllList = projectIntemDao.findList(new ProjectIntem());
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
	/**
	* @Description: 推送施工变更价格
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/7 17:03
	*/
	public boolean sendProjectItemPrice(ProjectItemPrice projectItemPrice){
//		查询所有的价格
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

	/**
	* @Description: 插入同步数据表
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/7 14:31
	*/
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
