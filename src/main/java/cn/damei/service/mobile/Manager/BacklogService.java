package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BacklogDao;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.entity.modules.ToDoItemEntity;



@Service
@Transactional(readOnly=true)
public class BacklogService  extends  CrudService2<BacklogDao,Backlog> {
@Autowired
private BacklogDao backlogDao;
	public List<Backlog> findOrderTaskPackageByManagerId(Integer id, String orderTaskpackageStatus80,
			String orderTaskpackageStatus95, String orderTaskpackageStatus110, String orderTaskpackageStatus130) {

		return backlogDao.findOrderTaskPackageByManagerId(id,orderTaskpackageStatus80,orderTaskpackageStatus95,orderTaskpackageStatus110,orderTaskpackageStatus130);
	}
	public List<Backlog> findApplyMaterial(Integer id) {

		
		List<Backlog> list = backlogDao.findAllOrderNumber(id);
		
		return list;
		
	}
	public List<ToDoItemEntity> findTodayTodo(Integer managerId,String orderId){
		
		return backlogDao.findTodayTodo(managerId, orderId);
	}
	public List<ToDoItemEntity> findOtherTodo(Integer managerId,String orderId){
		
		return backlogDao.findOtherTodo(managerId,orderId);
	}
	public List<Backlog>findOrderByManagerId(Integer managerId){
		
		return backlogDao.findOrderByManagerId( managerId);
	}
	public Backlog getErQiKuanInfo(String id){
		return backlogDao.getErQiKuanInfo(id);
	}
	public Backlog getErQiKuanInfoByItemId(String id){
		return backlogDao.getErQiKuanInfoByItemId(id);
	}
	public int findTotalCountTodo(Integer managerId){
		return backlogDao.findTotalCountTodo(managerId);
	}
	public void insertUrgePayByOrderId(Integer orderId){
		backlogDao.insertUrgePayByOrderId(orderId);
	}
}
