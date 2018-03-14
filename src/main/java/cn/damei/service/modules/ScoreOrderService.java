
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
	
    private static Integer EVALUATE_BAD = 0;
    private static String EVALUATE_BAD_COMPLAINT_TYPE = "16";
    
	public ScoreOrder get(String id) {
		return super.get(id);
	}
	
	public List<ScoreOrder> findList(ScoreOrder scoreOrder) {
		return super.findList(scoreOrder);
	}
	

	public List<Map<String,String>> selectOrderByCustomer(String customerPhone){
		
		return scoreOrderDao.selectOrderByCustomer(customerPhone);
		
	}
	

	public List<Map<String,String>> orderNoScoreType(Integer orderId){
		Map<String,Object> param = new HashMap<>();
		param.put("orderId", orderId);
		param.put("finalFlag", scoreOrderDao.getOrderStatusById(orderId)  );
		
		return scoreOrderDao.orderNoScoreType(param);
	}
	

	public List<Map<String,String>> orderHistoryScore(Integer orderId){
		return scoreOrderDao.orderHistoryScore(orderId);
	}
	
	public Page<ScoreOrder> findPage(Page<ScoreOrder> page, ScoreOrder scoreOrder) {
		return super.findPage(page, scoreOrder);
	}
	

	@Transactional
	public int orderScoreSave(Integer orderId,String scoreType,Integer scoreValue,String scoreContent) {
		int result = 0;
		try{
			
			Date date = new Date();
			String stakeholder = "";
			

			List<Map<String,Object>> memberList = null;
			if( ("Z001").equals( scoreType ) ){

			}else if( ("A003").equals( scoreType ) ){
				memberList = this.scoreOrderDao.selectServiceMemberFromOrder(orderId);
			}else if( ("A002").equals( scoreType ) ){
				memberList = this.scoreOrderDao.selectDesignerMemberFromOrder(orderId);
			}else{
				memberList = this.scoreOrderDao.selectTeamMemberByMap(orderId, scoreType);
			}
			
			if( memberList != null && memberList.size() > 0 ){
				for( Map<String,Object> map : memberList){

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
	

	public List<Map<String,String>> getComplainType(){
		return scoreCustomerComplaintDao.getComplainType();
	}
	

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
	

	public List<Map<String,Object>> selectAllTeamMember(Integer orderId){
		return scoreOrderDao.selectAllTeamMember(orderId);
	}
	

	@Transactional
	public void evaluateSave(Integer orderId,Integer evaluateType,String evaluateContent,Integer employeeId,String employeeName,String employeePhone,Integer storeId,String memberPost ) {
		try{
			Date date = new Date();
			

			ScoreTeamMemberEvaluate evaluate = new ScoreTeamMemberEvaluate();
			evaluate.setCreateDate(date);
			evaluate.setOrderId(orderId);
			evaluate.setEmployeeId(employeeId);
			evaluate.setEmployeeName(employeeName);
			evaluate.setEmployeePhone(employeePhone);
			evaluate.setEvaluateType( evaluateType.toString() );
			evaluate.setEvaluateTime(date);
			
			this.scoreTeamMemberEvaluateDao.insert( evaluate );
			

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


	
	public List<ScoreOrderQuery> selectOrderScoreQuery(Page <ScoreOrderQuery> page,ScoreOrderQuery scoreOrderQuery){
		scoreOrderQuery.setPage(page);
		List<ScoreOrderQuery> selectOrderScore = scoreOrderDao.selectOrderScoreQuery(scoreOrderQuery);
		return selectOrderScore;
	}


	public List<ScoreOrderComplain> selectScoreOrderComplainQuery(Page <ScoreOrderComplain> page ,ScoreOrderComplain orderComplain){
		orderComplain.setPage(page);
		return scoreOrderDao.selectScoreOrderComplainQuery(orderComplain);
	}

	public List<ScorOrderEmployee> selectScorOrderEmployeeQuery(Page <ScorOrderEmployee> page,ScorOrderEmployee scorOrderEmployee){
		scorOrderEmployee.setPage(page);
		return scoreOrderDao.selectScorOrderEmployeeQuery(scorOrderEmployee);
	}

	public List<Map<String,Object>> getScoreContentByStoreId(String storeId){
		return scoreOrderDao.getScoreContentByStoreId(storeId);
	}

	public List<Map<String,Object>> selectPositionTypeByStoreId(String storeId){
		return scoreOrderDao.selectPositionTypeByStoreId(storeId);
	}


	public int evaluateValidate(Integer orderId,Integer employeeId,String employeeName,String employeePhone) {

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Map<String,Object> param = new HashMap<>();
		param.put("employeeName", employeeName);
		param.put("employeePhone", employeePhone);
		param.put("orderId", orderId);
		param.put("evaluateTime", sdf.format(new Date()));
		
		return this.scoreTeamMemberEvaluateDao.evaluateValidate(param);
	}
	
	
	

	private String createComplainCode(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		return sdf.format(new Date());
	}

}