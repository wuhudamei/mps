package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ToDoItemEntity;

@MyBatisDao
public interface ToDoItemDao extends CrudDao<ToDoItemEntity>{
	public void updateStatusById(ToDoItemEntity toDoItemEntity);
	public void updateViewdByObj(String id);
	public void updateSolvedByObj(String id);
	public void updateViewedAndSolvedByObj(String id);
	public String selectId(Map map);
	public void saveDealUrlById(String id,String dealUrl);
	void updateUrgePayStatusByItemId(String id,String status);

	Map<String,Object> getToDoInfoByMap(Map<String,String> map);


	String findNextIdByPreId(String qcNodeId);



	void updateToDoItemInfoByOrderAndManagerId(Integer orderId,Integer managerId);


	int findIsUrgePay(String qcNodeId);
}
