
package cn.damei.dao.modules;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScorOrderEmployee;
import cn.damei.entity.modules.ScoreOrder;
import cn.damei.entity.modules.ScoreOrderComplain;
import cn.damei.entity.modules.ScoreOrderQuery;


@MyBatisDao
public interface ScoreOrderDao extends CrudDao<ScoreOrder> {

	List<Map<String,String>> selectOrderByCustomer(@Param("customerPhone") String customerPhone);
	
	
	int getOrderStatusById(Integer orderId);
	

	List<Map<String,String>> orderNoScoreType(Map<String,Object> map);
	

	List<Map<String,String>> orderHistoryScore(Integer orderId);
	

	List<Map<String,Object>> selectDesignerMemberFromOrder(Integer orderId);
	

	List<Map<String,Object>> selectServiceMemberFromOrder(Integer orderId);
	

	List<Map<String,Object>> selectTeamMemberByMap(@Param("orderId")Integer orderId,@Param("scoreType")String scoreType);

	List<Map<String,Object>> getScoreContent();

	List<Map<String,Object>> getScoreContentByStoreId(String storeId);

	List<Map<String,Object>> getScoreComplain();
	
	

	List<ScoreOrderQuery> selectOrderScoreQuery(ScoreOrderQuery scoreOrderQuery);

	List<ScoreOrderComplain> selectScoreOrderComplain();

	List<ScoreOrderComplain> selectScoreOrderComplainQuery(ScoreOrderComplain scoreOrderComplain);

	List<ScorOrderEmployee> selectScorOrderEmployeeQuery(ScorOrderEmployee scorOrderEmployee);

	List<Map<String,Object>> selectPositionType();
	

	List<Map<String,Object>> selectPositionTypeByStoreId(String storeId);
	

	List<Map<String,Object>> selectAllTeamMember(Integer orderId);
}