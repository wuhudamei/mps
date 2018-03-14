/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ScoreCustomerComplaintDao;
import cn.damei.dao.modules.ScoreOrderDao;
import cn.damei.dao.modules.ScoreTeamMemberActualDao;
import cn.damei.dao.modules.ScoreTeamMemberEvaluateDao;
import cn.damei.dao.modules.ScoreTeamMemberHistoryDao;
import cn.damei.entity.modules.ScorOrderEmployee;
import cn.damei.entity.modules.ScoreOrder;
import cn.damei.entity.modules.ScoreOrderComplain;
import cn.damei.entity.modules.ScoreOrderQuery;
import cn.damei.entity.modules.ScoreTeamMemberActual;
import cn.damei.entity.modules.ScoreTeamMemberEvaluate;
import cn.damei.entity.modules.ScoreTeamMemberHistory;
import cn.damei.entity.modules.ScorebizCustomerComplaint;

/**
 * 评分订单Service
 * @author liwc
 * @version 2017-04-12
 */
@Service
public class ScoreOrderService extends CrudService<ScoreOrderDao, ScoreOrder> {
	
	@Autowired
	private ScoreOrderDao scoreOrderDao;
	@Autowired
	ScoreTeamMemberActualDao scoreTeamMemberActualDao;
	@Autowired
	ScoreTeamMemberHistoryDao scoreTeamMemberHistoryDao;
	@Autowired
	ScoreTeamMemberEvaluateDao scoreTeamMemberEvaluateDao;
	@Autowired
	ScoreCustomerComplaintDao scoreCustomerComplaintDao;
	
    private static Integer EVALUATE_BAD = 0;					//差评
    private static String EVALUATE_BAD_COMPLAINT_TYPE = "16";	//差评默认投诉类型（其他）
    
	public ScoreOrder get(String id) {
		return super.get(id);
	}
	
	public List<ScoreOrder> findList(ScoreOrder scoreOrder) {
		return super.findList(scoreOrder);
	}
	
	/**
	 * 根据顾客电话查询订单
	 * @param customerPhone，用户手机号
	 * @return
	 */
	public List<Map<String,String>> selectOrderByCustomer(String customerPhone){
		
		return scoreOrderDao.selectOrderByCustomer(customerPhone);
		
	}
	
	/**
	 * 根据订单id获取未评分类型数据
	 * @param orderId
	 * @return
	 */
	public List<Map<String,String>> orderNoScoreType(Integer orderId){
		Map<String,Object> param = new HashMap<>();
		param.put("orderId", orderId);
		param.put("finalFlag", scoreOrderDao.getOrderStatusById(orderId)  );
		
		return scoreOrderDao.orderNoScoreType(param);
	}
	
	/**
	 * 根据订单id获取已评分类型历史数据
	 * @param orderId
	 * @return
	 */
	public List<Map<String,String>> orderHistoryScore(Integer orderId){
		return scoreOrderDao.orderHistoryScore(orderId);
	}
	
	public Page<ScoreOrder> findPage(Page<ScoreOrder> page, ScoreOrder scoreOrder) {
		return super.findPage(page, scoreOrder);
	}
	
	/**
	 * 订单评分提交处理
	 * @param orderId
	 * @param scoreType
	 * @param scoreValue
	 * @param scoreContent
	 */
	@Transactional
	public int orderScoreSave(Integer orderId,String scoreType,Integer scoreValue,String scoreContent) {
		int result = 0;
		try{
			
			Date date = new Date();
			String stakeholder = "";
			
			//1、获取评分类型对应的员工信息
			List<Map<String,Object>> memberList = null;
			if( ("Z001").equals( scoreType ) ){
				//暂时不对任何人评分
			}else if( ("A003").equals( scoreType ) ){
				memberList = this.scoreOrderDao.selectServiceMemberFromOrder(orderId);
			}else if( ("A002").equals( scoreType ) ){
				memberList = this.scoreOrderDao.selectDesignerMemberFromOrder(orderId);
			}else{
				memberList = this.scoreOrderDao.selectTeamMemberByMap(orderId, scoreType);
			}
			
			if( memberList != null && memberList.size() > 0 ){
				for( Map<String,Object> map : memberList){
					//2、增加员工历史评分记录
					if( map.get("memberName") != null && !map.get("memberName").equals("") &&  map.get("memberPhone") != null && !map.get("memberPhone").equals("")){
						ScoreTeamMemberHistory historyScore = new ScoreTeamMemberHistory();
						historyScore.setOrderId(orderId);
						historyScore.setEmployeeId( map.get("id") != null && !map.get("id").equals("") ? Integer.parseInt(map.get("id").toString() ) : null );
						historyScore.setEmployeeName( map.get("memberName").toString() );
						historyScore.setEmployeePhone( map.get("memberPhone").toString() );
						historyScore.setScoreType(scoreType);
						historyScore.setScoreValue(scoreValue);
						historyScore.setScoreTime( date );
						
						this.scoreTeamMemberHistoryDao.insert(historyScore);
						
						//3、更新员工当前评分(先根据用户信息查询是否存在评分信息，如果存在更新，如果不存在添加)
						Map<String,Object> param = new HashMap<>();
						param.put("employeeName", map.get("memberName").toString());
						param.put("employeePhone", map.get("memberPhone").toString());
						ScoreTeamMemberActual actual = this.scoreTeamMemberActualDao.selectByEmployee(param);
						if( actual != null  ){
							
							if( actual.getInitFlag().equals("0") ){
								param.put("currentScore", scoreValue);
								param.put("initFlag", "1");
							}else{
								Integer oldScore = actual.getCurrentScore();
								param.put("currentScore", ( oldScore + scoreValue )/2);
							}
							
							this.scoreTeamMemberActualDao.updateEmployee(param);
						}else{
							actual = new ScoreTeamMemberActual();
							
							actual.setEmployeeId( map.get("id") != null ? Integer.parseInt(map.get("id").toString() ) : null );
							actual.setEmployeeName( map.get("memberName").toString() );
							actual.setEmployeePhone( map.get("memberPhone").toString() );
							actual.setBelongOffice( Integer.parseInt(map.get("storeId").toString() ) );
							actual.setEmployeePost( map.get("memberPost").toString() );
							actual.setCurrentScore(scoreValue);
							actual.setCurrentGoodNum(0);
							actual.setCurrentBadNum(0);
							actual.setInitFlag("1");
							
							this.scoreTeamMemberActualDao.insert(actual);
						}
						stakeholder = stakeholder + map.get("memberName").toString() + ",";
					}
				}
			}
			
			//4、保存订单评分
			
			if( !StringUtils.isBlank( stakeholder ) ){
				stakeholder = stakeholder.substring(0, stakeholder.length()-1);
			}
			
			ScoreOrder scoreOrder = new ScoreOrder();
			scoreOrder.setOrderId(orderId);
			scoreOrder.setScoreType(scoreType);
			scoreOrder.setScoreValue(scoreValue);
			scoreOrder.setScoreContent(scoreContent);
			scoreOrder.setScoreTime(date);
			scoreOrder.setStakeholder(stakeholder);
			scoreOrder.setCreateDate(date);
			
			this.scoreOrderDao.insert(scoreOrder);
			result = 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 *获取投诉类型数据
	 * @return
	 */
	public List<Map<String,String>> getComplainType(){
		return scoreCustomerComplaintDao.getComplainType();
	}
	
	/**
	 * 顾客投诉提交操作
	 * @param orderId
	 * @param complainType
	 * @param complainContent
	 */
	@Transactional
	public void commitComplain(Integer orderId,String complainType,String complainContent) {
		Date date = new Date();
		ScorebizCustomerComplaint  complaint = new ScorebizCustomerComplaint();
		
		complaint.setComplainCode(createComplainCode());
		complaint.setOrderId(orderId);
		complaint.setComplainType(complainType);
		complaint.setComplainContent(complainContent);
		complaint.setComplainTime(date);
		complaint.setCreateDate( date );
		this.scoreCustomerComplaintDao.insert( complaint );
	}
	
	/**
	 * 根据订单id获取全部的干系人，包括项目经理，服务人员，设计人员，施工团队
	 * @param orderId
	 * @return
	 */
	public List<Map<String,Object>> selectAllTeamMember(Integer orderId){
		return scoreOrderDao.selectAllTeamMember(orderId);
	}
	
	/**
	 * 人员评价操作
	 * @param orderId
	 * @param evaluateType
	 * @param evaluateContent
	 * @param employeeId
	 * @param employeeName
	 * @param employeePhone
	 */
	@Transactional
	public void evaluateSave(Integer orderId,Integer evaluateType,String evaluateContent,Integer employeeId,String employeeName,String employeePhone,Integer storeId,String memberPost ) {
		try{
			Date date = new Date();
			
			//1、保存评价记录
			ScoreTeamMemberEvaluate evaluate = new ScoreTeamMemberEvaluate();
			evaluate.setCreateDate(date);
			evaluate.setOrderId(orderId);
			evaluate.setEmployeeId(employeeId);
			evaluate.setEmployeeName(employeeName);
			evaluate.setEmployeePhone(employeePhone);
			evaluate.setEvaluateType( evaluateType.toString() );
			evaluate.setEvaluateTime(date);
			
			this.scoreTeamMemberEvaluateDao.insert( evaluate );
			
			//2、更新员工实际点赞数或者差评数
			Map<String,Object> param = new HashMap<>();
			param.put("employeeName", employeeName);
			param.put("employeePhone", employeePhone);
			
			ScoreTeamMemberActual actual = this.scoreTeamMemberActualDao.selectByEmployee(param);
			if( actual != null ){
				if( EVALUATE_BAD.equals( evaluateType ) ){
					param.put("currentBadNum", actual.getCurrentBadNum() + 1 );
				}else{
					param.put("currentGoodNum", actual.getCurrentGoodNum() + 1 );
				}
				this.scoreTeamMemberActualDao.updateEmployee(param);
			}else{
				actual = new ScoreTeamMemberActual();
				
				actual.setEmployeeId( employeeId );
				actual.setEmployeeName( employeeName );
				actual.setEmployeePhone( employeePhone );
				actual.setBelongOffice( storeId );
				actual.setEmployeePost( memberPost );
				
				actual.setCurrentScore(0);
				if( EVALUATE_BAD.equals( evaluateType ) ){
					actual.setCurrentGoodNum(0);
					actual.setCurrentBadNum(1);
				}else{
					actual.setCurrentGoodNum(1);
					actual.setCurrentBadNum(0);
				}
				
				actual.setInitFlag("0");
				
				this.scoreTeamMemberActualDao.insert(actual);
			}
			
			//对于差评部分，增加投诉记录
			if( EVALUATE_BAD.equals( evaluateType ) ){
				ScorebizCustomerComplaint  complaint = new ScorebizCustomerComplaint();
				
				complaint.setComplainCode(createComplainCode());
				complaint.setOrderId(orderId);
				complaint.setComplainType( EVALUATE_BAD_COMPLAINT_TYPE );
				complaint.setComplainContent(evaluateContent);
				complaint.setComplainTime(date);
				complaint.setCreateDate( date );
				complaint.setEmployeeId(employeeId);
				complaint.setEmployeeName(employeeName);
				complaint.setEmployeePhone(employeePhone);
				
				this.scoreCustomerComplaintDao.insert( complaint );
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 根据查询条件 查询所有订单评分统计
	 * @param address
	 * @return 
	 */
	
	public List<ScoreOrderQuery> selectOrderScoreQuery(Page <ScoreOrderQuery> page,ScoreOrderQuery scoreOrderQuery){
		scoreOrderQuery.setPage(page);
		List<ScoreOrderQuery> selectOrderScore = scoreOrderDao.selectOrderScoreQuery(scoreOrderQuery);
		return selectOrderScore;
	}
/*	public List<ScoreOrderComplain> selectScoreOrderComplain(){
		return scoreOrderDao.selectScoreOrderComplain();
	}*/
	/**
	 * 根据查询条件 查询所有客诉统计
	 * @param
	 * @return 
	 */
	public List<ScoreOrderComplain> selectScoreOrderComplainQuery(Page <ScoreOrderComplain> page ,ScoreOrderComplain orderComplain){
		orderComplain.setPage(page);
		return scoreOrderDao.selectScoreOrderComplainQuery(orderComplain);
	}
	/**
	 * 根据查询条件 查询所有员工评分统计
	 * @param 
	 * @return 
	 */
	public List<ScorOrderEmployee> selectScorOrderEmployeeQuery(Page <ScorOrderEmployee> page,ScorOrderEmployee scorOrderEmployee){
		scorOrderEmployee.setPage(page);
		return scoreOrderDao.selectScorOrderEmployeeQuery(scorOrderEmployee);
	}
	/**
	 * 根据公司获取评分类型
	 * @return
	 */
	public List<Map<String,Object>> getScoreContentByStoreId(String storeId){
		return scoreOrderDao.getScoreContentByStoreId(storeId);
	}
	/**
	 * 根据公司查询  岗位列表
	 * @return
	 */
	public List<Map<String,Object>> selectPositionTypeByStoreId(String storeId){
		return scoreOrderDao.selectPositionTypeByStoreId(storeId);
	}

	/**
	 * 根据订单iD验证当天是否对该用户进行评价
	 * @param orderId
	 * @param employeeId
	 * @param employeeName
	 * @param employeePhone
	 * @return
	 */
	public int evaluateValidate(Integer orderId,Integer employeeId,String employeeName,String employeePhone) {
		//校验是否可操作
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Map<String,Object> param = new HashMap<>();
		param.put("employeeName", employeeName);
		param.put("employeePhone", employeePhone);
		param.put("orderId", orderId);
		param.put("evaluateTime", sdf.format(new Date()));
		
		return this.scoreTeamMemberEvaluateDao.evaluateValidate(param);
	}
	
	
	
	/**
	 * 生成客诉编码
	 * @return
	 */
	private String createComplainCode(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		return sdf.format(new Date());
	}

}