package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.entity.modules.ToDoItemEntity;
@MyBatisDao
public interface BacklogDao extends CrudDao2<Backlog>{

	List<Backlog> findOrderTaskPackageByManagerId(Integer id, String orderTaskpackageStatus80,
			String orderTaskpackageStatus95, String orderTaskpackageStatus110, String orderTaskpackageStatus130);

	List<String> findAllOrderIDByID(Integer id);

	List<Backlog> findNoApplyMaterial(List<String> allId);

	List<String> findNoApplyMaterialId(List<String> allId);

	List<Backlog> findNoApplyMaterialAll(List<String> allId);

	List<Backlog> findAllOrderNumber(Integer id);
	List<ToDoItemEntity> findTodayTodo(Integer managerId,String orderId);
	List<ToDoItemEntity> findOtherTodo(Integer managerId,String orderId);
	List<Backlog>findOrderByManagerId(Integer managerId);
	Backlog getErQiKuanInfo(String id);
	Backlog getErQiKuanInfoByItemId(String id);
	int findTotalCountTodo(Integer managerId);
	void insertUrgePayByOrderId(Integer orderId);
}
