package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ToDoItemEntity;
/**
 * 待办事项dao
 * @author lzm
 * @version 2017-7-14
 */
@MyBatisDao
public interface ToDoItemDao extends CrudDao<ToDoItemEntity>{
	public void updateStatusById(ToDoItemEntity toDoItemEntity);
	public void updateViewdByObj(String id);
	public void updateSolvedByObj(String id);
	public void updateViewedAndSolvedByObj(String id);
	public String selectId(Map map);
	public void saveDealUrlById(String id,String dealUrl);
	void updateUrgePayStatusByItemId(String id,String status);
	/**
	 * orderId,relatedBusinessType, |qcNodeId
	 * @param map
	 * @return
	 */
	Map<String,Object> getToDoInfoByMap(Map<String,String> map);

	/**
	 * 找到下一个节点的id
	 * @param qcNodeId
	 * @return
	 */
	String findNextIdByPreId(String qcNodeId);


	/**
	 * 重拍经理更新数据
	 */
	void updateToDoItemInfoByOrderAndManagerId(Integer orderId,Integer managerId);


	int findIsUrgePay(String qcNodeId);
}
