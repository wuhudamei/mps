
package cn.damei.service.modules;

import java.text.NumberFormat;
import java.util.*;

import cn.damei.dao.modules.BizCustomerReturnVisitTraditionOrderDataDao;
import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import cn.damei.entity.modules.Dict;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizCustomerReturnVisitDao;
import cn.damei.dao.modules.BizCustomerReturnVisitRecordAnswerDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecordAnswer;
import cn.damei.dao.modules.BizCustomerReturnVisitInvalidRecordDao;
import cn.damei.dao.modules.BizCustomerReturnVisitOrderEnableDao;
import cn.damei.dao.modules.BizCustomerReturnVisitRecordDao;
import cn.damei.entity.modules.BizCustomerReturnVisitInvalidRecord;
import cn.damei.entity.modules.BizCustomerReturnVisitOrderEnable;
import cn.damei.entity.modules.BizCustomerReturnVisitRecord;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Service
public class BizCustomerReturnVisitRecordService extends CrudService2<BizCustomerReturnVisitRecordDao, BizCustomerReturnVisitRecord> {
	
	@Autowired
	BizCustomerReturnVisitDao returnVisitDao;
	
	@Autowired
	BizCustomerReturnVisitRecordDao returnVisitRecordDao;
	
	@Autowired
	BizCustomerReturnVisitInvalidRecordDao invalidRecordDao;
	
	@Autowired
	BizCustomerReturnVisitRecordAnswerDao returnVisitRecordAnswerDao;
	
	@Autowired
	BizCustomerReturnVisitOrderEnableDao returnVisitOrderEnableDao;

	@Autowired
	BizCustomerReturnVisitTraditionOrderDataDao bizCustomerReturnVisitTraditionOrderDataDao;
	
	public BizCustomerReturnVisitRecord get(Integer id) {
		return super.get(id);
	}
	
	public List<BizCustomerReturnVisitRecord> findList(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		return super.findList(bizCustomerReturnVisitRecord);
	}
	
	public Page<BizCustomerReturnVisitRecord> findPage(Page<BizCustomerReturnVisitRecord> page, BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		return super.findPage(page, bizCustomerReturnVisitRecord);
	}
	

	public Page<BizCustomerReturnVisitRecord> findPageForChecking(Page<BizCustomerReturnVisitRecord> page,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		bizCustomerReturnVisitRecord.setPage(page);
		page.setList(returnVisitRecordDao.findPageForChecking(bizCustomerReturnVisitRecord));
		return page;
	}
	

	public Page<BizCustomerReturnVisitRecord> findPageForChecking1(Page<BizCustomerReturnVisitRecord> page,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		bizCustomerReturnVisitRecord.setPage(page);
		page.setList(returnVisitRecordDao.findPageForChecking1(bizCustomerReturnVisitRecord));
		return page;
	}
	

	public Map<String,String> getOrderInfoForCheck(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord){
		return returnVisitRecordDao.getOrderInfoForCheck(bizCustomerReturnVisitRecord);
	}
	

	@Transactional()
	public void saveStop(Integer id) {
		User user = UserUtils.getUser();
		Date date = new Date();
		BizCustomerReturnVisitOrderEnable bizCustomerReturnVisitOrderEnable=new BizCustomerReturnVisitOrderEnable();
		if (StringUtils.isNotBlank(user.getId())){	
			bizCustomerReturnVisitOrderEnable.setCreateBy( user);
			bizCustomerReturnVisitOrderEnable.setOrderId(id);
			bizCustomerReturnVisitOrderEnable.setIsEnabled(0);
		}
		bizCustomerReturnVisitOrderEnable.setCreateDate( date );
		returnVisitOrderEnableDao.insert(bizCustomerReturnVisitOrderEnable);
	}
	@Transactional()
	public void updateStop(Integer id) {
		User user = UserUtils.getUser();
		Date date = new Date();
		BizCustomerReturnVisitOrderEnable bizCustomerReturnVisitOrderEnable=new BizCustomerReturnVisitOrderEnable();
		if (StringUtils.isNotBlank(user.getId())){	
			bizCustomerReturnVisitOrderEnable.setCreateBy( user);
			bizCustomerReturnVisitOrderEnable.setOrderId(id);
			bizCustomerReturnVisitOrderEnable.setIsEnabled(0);
		}
		bizCustomerReturnVisitOrderEnable.setCreateDate( date );
		returnVisitOrderEnableDao.update(bizCustomerReturnVisitOrderEnable);
		
	}


	@Transactional()
	public void deleteStop(Integer id) {
		User user = UserUtils.getUser();
		Date date = new Date();
		BizCustomerReturnVisitOrderEnable bizCustomerReturnVisitOrderEnable=new BizCustomerReturnVisitOrderEnable();
		if (StringUtils.isNotBlank(user.getId())){	
			bizCustomerReturnVisitOrderEnable.setCreateBy( user);
			bizCustomerReturnVisitOrderEnable.setOrderId(id);
			bizCustomerReturnVisitOrderEnable.setIsEnabled(1);
		}
		bizCustomerReturnVisitOrderEnable.setCreateDate( date );
		returnVisitOrderEnableDao.delete(bizCustomerReturnVisitOrderEnable);
	}
	

	@Transactional()
	public void save(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,
			String[] questionContent,
			String[] statisticsDepartment,
			String[] statisticsResult,
			String[] questionAnswer
			) {
		User user = UserUtils.getUser();
		Date date = new Date();
		
		if (StringUtils.isNotBlank(user.getId())){
			bizCustomerReturnVisitRecord.setCreateBy( user );
			bizCustomerReturnVisitRecord.setReturnVisitTime( date );
			bizCustomerReturnVisitRecord.setCustomServiceId(user.getId());
			bizCustomerReturnVisitRecord.setCustomServiceName(user.getName());
		}
		bizCustomerReturnVisitRecord.setCreateDate( date );
		returnVisitRecordDao.insert(bizCustomerReturnVisitRecord);
		

		List<BizCustomerReturnVisitRecordAnswer> contentList = new ArrayList<BizCustomerReturnVisitRecordAnswer>();
		for(int i = 0 ; i < questionContent.length; i++){
			BizCustomerReturnVisitRecordAnswer answer = new BizCustomerReturnVisitRecordAnswer();
			
			answer.setReturnVisitRecordId( bizCustomerReturnVisitRecord.getId() );
			answer.setReturnVisitQuestion(questionContent[i]);
			answer.setQuestionAnswer(questionAnswer[i]);
			answer.setStatisticsDepartment( Integer.parseInt( statisticsDepartment[i] ) );
			answer.setStatisticsResult( statisticsResult[i] );
			if (StringUtils.isNotBlank(user.getId())){
				answer.setCreateBy( user );
			}
			answer.setCreateDate( date );
			contentList.add(answer);
		}
		returnVisitRecordAnswerDao.insertBatch(contentList);
	}
	

	@Transactional()
	public void saveInvalidRecord(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord,String invalidReason) {
		User user = UserUtils.getUser();
		Date date = new Date();
		BizCustomerReturnVisitInvalidRecord invalidRecord = new BizCustomerReturnVisitInvalidRecord();
		invalidRecord.setOrderId( bizCustomerReturnVisitRecord.getOrderId() );
		invalidRecord.setInvalidReason(invalidReason);
		invalidRecord.setReturnVisitNode( bizCustomerReturnVisitRecord.getReturnVisitNode() );
		invalidRecord.setReturnVisitNodeName( bizCustomerReturnVisitRecord.getReturnVisitNodeName() );
		if (StringUtils.isNotBlank(user.getId())){
			invalidRecord.setCreateBy( user );
			invalidRecord.setReturnVisitTime( date );
			invalidRecord.setCustomServiceId(user.getId());
			invalidRecord.setCustomServiceName(user.getName());
		}
		invalidRecord.setCreateDate( date );
		
		invalidRecordDao.insertInvalidRecord(invalidRecord);
	}
	

	public List<BizCustomerReturnVisitRecordAnswer> getReturnVisitRecordQuestion(Integer returnVisitRecordId){
		return returnVisitRecordAnswerDao.getListByRecordId(returnVisitRecordId);
	}
	

	public Map<String,Object> visitRecordSummaryQuery(Map<String,String> param){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		

		List<String> returnVisitNode = new ArrayList<String>();
		List<String> returnVisitNodeNames = new ArrayList<String>();
		returnVisitNode.add("node_9999");
		returnVisitNodeNames.add("无效回访");
		
		List<Map<String,Object>> returnVisitNodes = returnVisitDao.queryReturnVisitNodeByStoreId( param.get("storeId"), param.get("projectMode"));
		for( Map<String,Object> map : returnVisitNodes){
			returnVisitNode.add( "node_" + map.get("value").toString()  );
			returnVisitNodeNames.add( map.get("label").toString() );
		}
		
		resultMap.put("nodeArray", returnVisitNode );
		resultMap.put("titleArray", returnVisitNodeNames );
		
		String custom_service_name = "";
		Integer totalVisitNum = 0;
		Integer failVisitNum = 0;
		Map<String,String> tempMap = new HashMap<String,String>();
		
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		
		List<Map<String,Object>> list = returnVisitRecordDao.visitRecordSummaryQuery(param);
		if( list != null && list.size() > 0 ){
			for( Map<String,Object> map : list){
				if( !map.get("custom_service_name").equals(custom_service_name) ){
					
					if( !StringUtils.isBlank( custom_service_name ) ){
						tempMap.put("totalVisitNum", totalVisitNum.toString());
						tempMap.put("failVisitNum", failVisitNum.toString());
						tempMap.put("successVisitNum", (totalVisitNum-failVisitNum) + "" );
						resultList.add( tempMap );
						
						tempMap = null;
						tempMap = new HashMap<String,String>();
						totalVisitNum = 0;
						failVisitNum = 0;
					}
					
					tempMap.put("storeId", param.get("storeId") );
					tempMap.put("custom_service_name", map.get("custom_service_name").toString());
					custom_service_name = map.get("custom_service_name").toString();
				}
				
				for( String str : returnVisitNode ){
					if( map.get("visit_node") != null && str.equals( map.get("visit_node").toString() )){
						tempMap.put(str, map.get("num").toString());
						totalVisitNum = totalVisitNum + Integer.parseInt( map.get("num").toString() );
						

						if( ("node_9999").equals( map.get("visit_node") ) ){
							failVisitNum = Integer.parseInt( map.get("num").toString() );
						}
						break;
					}
				}
				
			}
			tempMap.put("totalVisitNum", totalVisitNum.toString());
			tempMap.put("failVisitNum", failVisitNum.toString());
			tempMap.put("successVisitNum", (totalVisitNum-failVisitNum) + "" );
			resultList.add( tempMap );
		}
		resultMap.put("resultList", resultList);
		return resultMap;
	}
	

	public List<Map<String,Object>> satisfactionDegreeQuery(Map<String,String> param,String[] satisfactionDegreeCodeArray,String[] satisfactionDegreeNameArray){
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		

		List<Map<String,Object>> list = returnVisitRecordDao.satisfactionDegreeQuery(param);
		if( list != null && list.size() > 0 ){
			for( Map<String,Object> map : list){
				
				Integer successNum = 0;
				Integer satisfiedNum = 0;
				String statistic_item = map.get("statistic_item").toString();
				String temp  = "";

				if( StringUtils.isNotBlank( statistic_item ) ){
					statistic_item = statistic_item + ",";
					
					for( int i = 0;i < satisfactionDegreeNameArray.length ; i++ ){
						temp  = String.valueOf( statistic_item );
						String referenceStr = "|"+ satisfactionDegreeNameArray[i] + "|_";
						if( temp.indexOf( referenceStr ) > -1 ){
							int startNum = temp.indexOf(referenceStr);
							temp = temp.substring(startNum).replace(referenceStr, "");
							String result = temp.substring(0, temp.indexOf(","));
							
							map.put(satisfactionDegreeCodeArray[i], result);
							successNum += Integer.parseInt(result);
							if( i == 0 ){
								satisfiedNum = Integer.parseInt(result);
							}
						}else{
							map.put(satisfactionDegreeCodeArray[i], "0");
						}
					}
					
					map.put("successVisitNum", successNum);
					map.put("satisfactionDegree", getPercent(satisfiedNum,successNum) );
					
					successNum = 0;
					satisfiedNum = 0;
				}else{
					for(String str:satisfactionDegreeCodeArray){
						map.put(str, 0);
					}
					map.put("successVisitNum", "0");
					map.put("satisfactionDegree", "0.00");
				}
				
				resultList.add(map);
			}
		}
		
		return resultList;
	}
	

	public List<Map<String,Object>> getOrderHistoryVisitRecord(Integer orderId){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		

		List<Map<String,String>> list = returnVisitRecordDao.getOrderHistoryVisitRecord(orderId);
		String tempStr = "";
		Map<String,Object> baseMap = new HashMap<String,Object>();
		List<Map<String,String>> questionList = new ArrayList<Map<String,String>>(); 
		if( list != null && list.size() > 0 ){
			for( Map<String,String> map:list){
				if( !tempStr.equals( map.get("return_visit_node_name").toString() ) ){
					if( StringUtils.isNotBlank( tempStr ) ){
						
						baseMap.put("questionList", questionList);
						resultList.add(baseMap);
						
						baseMap = new HashMap<String,Object>();
						questionList = new ArrayList<Map<String,String>>();
					}
					
					tempStr =  map.get("return_visit_node_name").toString();
					baseMap.put("return_visit_node_name", tempStr);
				}

				questionList.add(map);
			}
			baseMap.put("questionList", questionList);
			resultList.add(baseMap);
		}
		
		return resultList;
	}
 	

	private static String getPercent(Integer cs,Integer bcs ){
		String percent ;
		Double p3 = 0.0;
		if(bcs == 0){
			p3 = 0.0;
		}else{
			p3 = cs*1.0/bcs;
		}
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		percent = nf.format(p3);
		return percent;
	}


	public Page<BizCustomerReturnVisitRecord> invalidList(Page<BizCustomerReturnVisitRecord> page,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		bizCustomerReturnVisitRecord.setPage(page);
		page.setList(returnVisitRecordDao.invalidList(bizCustomerReturnVisitRecord));
		return page;
	}

    public List<Dict> findProjectMode() {
		return returnVisitRecordDao.findProjectMode();
    }


    public Page<BizCustomerReturnVisitTraditionOrderData> findPageForTraditionOrder(Page<BizCustomerReturnVisitTraditionOrderData> page, BizCustomerReturnVisitTraditionOrderData bizCustomerReturnVisitTraditionOrderData) {
		bizCustomerReturnVisitTraditionOrderData.setPage(page);
		page.setList(bizCustomerReturnVisitTraditionOrderDataDao.findPageForTraditionOrder(bizCustomerReturnVisitTraditionOrderData));
		return page;
    }

	public List<BizCustomerReturnVisitTraditionOrderData> findReturnVisitNode(Integer orderId) {
		return bizCustomerReturnVisitTraditionOrderDataDao.findReturnVisitNode(orderId);
	}


	@Transactional()
	public void insertBizCustomerReturnVisitTraditionOrderData(BizCustomerReturnVisitTraditionOrderData bto){
		bizCustomerReturnVisitTraditionOrderDataDao.insert(bto);
	}

	public List<Map<String,Object>> queryReturnVisitNodeByStoreId(String storeId) {
		List<Map<String,Object>> result= bizCustomerReturnVisitTraditionOrderDataDao.queryReturnVisitNodeByStoreId(storeId);
		if( result != null && result.size() > 0 ){
			return result;
		}else{
			return Collections.emptyList();
		}
	}


	public Integer findExistCount(Integer orderId, Integer qcCheckNodeId) {
		return bizCustomerReturnVisitTraditionOrderDataDao.findExistCount(orderId,qcCheckNodeId);
	}


	@Transactional()
	public void updateStatusHaveDone(Integer orderId) {
		bizCustomerReturnVisitTraditionOrderDataDao.updateStatusHaveDone(orderId);
	}


	@Transactional()
	public void updateStatus(BizCustomerReturnVisitTraditionOrderData bto) {
		bizCustomerReturnVisitTraditionOrderDataDao.updateStatus(bto);
	}


	public List<Map<String,Object>> findIsThereNode(String storeId, Integer qcCheckNodeId) {
		return  bizCustomerReturnVisitTraditionOrderDataDao.findIsThereNode(storeId,qcCheckNodeId);
	}
}