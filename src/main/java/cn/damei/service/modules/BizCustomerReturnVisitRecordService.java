/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 客户回访记录Service
 * @author lft
 * @version 2017-05-26
 */
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
	
	/**
	 * 待回访订单列表查询
	 * @param page
	 * @param bizCustomerReturnVisitRecord
	 * @return
	 */
	public Page<BizCustomerReturnVisitRecord> findPageForChecking(Page<BizCustomerReturnVisitRecord> page,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		bizCustomerReturnVisitRecord.setPage(page);
		page.setList(returnVisitRecordDao.findPageForChecking(bizCustomerReturnVisitRecord));
		return page;
	}
	
	/**
	 * 回访管理查询
	 * @param page
	 * @param bizCustomerReturnVisitRecord
	 * @return
	 */
	public Page<BizCustomerReturnVisitRecord> findPageForChecking1(Page<BizCustomerReturnVisitRecord> page,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		bizCustomerReturnVisitRecord.setPage(page);
		page.setList(returnVisitRecordDao.findPageForChecking1(bizCustomerReturnVisitRecord));
		return page;
	}
	
	/**
	 * 根据订单ID和回访节点获取订单详情
	 * @param bizCustomerReturnVisitRecord
	 * @return
	 */
	public Map<String,String> getOrderInfoForCheck(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord){
		return returnVisitRecordDao.getOrderInfoForCheck(bizCustomerReturnVisitRecord);
	}
	
	/**
	 * 存储停止回访
	 * @param bizCustomerReturnVisitOrderEnable
	 */
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

	/**
	 * 启用回访
	 * @param bizCustomerReturnVisitOrderEnable
	 */
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
	
	/**
	 * 回访信息提交保存操作
	 * @param bizCustomerReturnVisitRecord
	 * @param questionContent
	 * @param questionAnswer
	 */
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
		
		//批量插入答案信息
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
	
	/**
	 * 无效回访提交
	 * @param bizCustomerReturnVisitRecord
	 * @param invalidReason
	 */
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
	
	/**
	 * 获取回访题目信息
	 * @param returnVisitRecordId
	 * @return
	 */
	public List<BizCustomerReturnVisitRecordAnswer> getReturnVisitRecordQuestion(Integer returnVisitRecordId){
		return returnVisitRecordAnswerDao.getListByRecordId(returnVisitRecordId);
	}
	
	/**
	 * 根据汇总结果组装结果展示
	 * @param param
	 * @return
	 */
	public Map<String,Object> visitRecordSummaryQuery(Map<String,String> param){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//查询汇总表头title
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
						
						//无效回访数量
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
	
	/**
	 * 满意度汇总查询组装数据
	 * @param param
	 * @param satisfactionDegreeCodeArray	统计维度编码数组
	 * @param satisfactionDegreeNameArray	统计维度名称数组
	 * @return
	 */
	public List<Map<String,Object>> satisfactionDegreeQuery(Map<String,String> param,String[] satisfactionDegreeCodeArray,String[] satisfactionDegreeNameArray){
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		/**查询汇总结果,格式如下
		 *  department_name			statistic_item
		 * 	木工						|满意|_1,|不满意|_1
			水电工					|满意|_3
		 */
		List<Map<String,Object>> list = returnVisitRecordDao.satisfactionDegreeQuery(param);
		if( list != null && list.size() > 0 ){
			for( Map<String,Object> map : list){
				
				Integer successNum = 0;
				Integer satisfiedNum = 0;
				String statistic_item = map.get("statistic_item").toString();
				String temp  = "";
				//如果有统计内容，则根据统计数组进行遍历整理数据
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
	
	/**
	 * 根据订单id查询该订单的回访历史记录信息，按照前端展示结构进行组装
	 * 格式如下：
	 * [
	 * 	{
	 * 		"return_visit_node_name":"开工回访",
	 * 		"questionList":[
	 * 			{
	 * 				"return_visit_question":"请问您家是哪天开始施工的呢？	",
	 * 				"question_answer":"2017/6/12"
	 * 			}
	 * 		]
	 * 	}
	 * ]
	 * @param orderId
	 * @return
	 */
	public List<Map<String,Object>> getOrderHistoryVisitRecord(Integer orderId){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		//实际回访记录
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
 	
	/**
	 * 计算百分比，保留两位小数
	 * @param cs
	 * @param bcs
	 * @return
	 */
	private static String getPercent(Integer cs,Integer bcs ){
		String percent ;
		Double p3 = 0.0;
		if(bcs == 0){
			p3 = 0.0;
		}else{
			p3 = cs*1.0/bcs;
		}
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);//控制保留小数点后几位，2：表示保留2位小数点
		percent = nf.format(p3);
		return percent;
	}

	/**
	 * 无效回访记录
	 * @param 
	 * @param 
	 * @return
	 */
	public Page<BizCustomerReturnVisitRecord> invalidList(Page<BizCustomerReturnVisitRecord> page,BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord) {
		bizCustomerReturnVisitRecord.setPage(page);
		page.setList(returnVisitRecordDao.invalidList(bizCustomerReturnVisitRecord));
		return page;
	}
	/**
	 * 过滤工程模式
	 * @param
	 * @param
	 * @return
	 */
    public List<Dict> findProjectMode() {
		return returnVisitRecordDao.findProjectMode();
    }

	/**
	 * 传统待回访订单
	 * @param
	 * @param
	 * @return
	 */
    public Page<BizCustomerReturnVisitTraditionOrderData> findPageForTraditionOrder(Page<BizCustomerReturnVisitTraditionOrderData> page, BizCustomerReturnVisitTraditionOrderData bizCustomerReturnVisitTraditionOrderData) {
		bizCustomerReturnVisitTraditionOrderData.setPage(page);
		page.setList(bizCustomerReturnVisitTraditionOrderDataDao.findPageForTraditionOrder(bizCustomerReturnVisitTraditionOrderData));
		return page;
    }

	public List<BizCustomerReturnVisitTraditionOrderData> findReturnVisitNode(Integer orderId) {
		return bizCustomerReturnVisitTraditionOrderDataDao.findReturnVisitNode(orderId);
	}

	//向传统回访订单表中插入数据
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

	//判断传统订单表中是否插入过该订单对应的节点数据
	public Integer findExistCount(Integer orderId, Integer qcCheckNodeId) {
		return bizCustomerReturnVisitTraditionOrderDataDao.findExistCount(orderId,qcCheckNodeId);
	}

	//传统订单将状态更新为已回访2
	@Transactional()
	public void updateStatusHaveDone(Integer orderId) {
		bizCustomerReturnVisitTraditionOrderDataDao.updateStatusHaveDone(orderId);
	}

	//传统订单将状态更新为已过期0
	@Transactional()
	public void updateStatus(BizCustomerReturnVisitTraditionOrderData bto) {
		bizCustomerReturnVisitTraditionOrderDataDao.updateStatus(bto);
	}

	//查询回访节点表是否设置过这个节点
	public List<Map<String,Object>> findIsThereNode(String storeId, Integer qcCheckNodeId) {
		return  bizCustomerReturnVisitTraditionOrderDataDao.findIsThereNode(storeId,qcCheckNodeId);
	}
}