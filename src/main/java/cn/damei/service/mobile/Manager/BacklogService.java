package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BacklogDao;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.entity.modules.ToDoItemEntity;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月28日 上午10:14:21 
* 类说明 
*/

@Service
@Transactional(readOnly=true)
public class BacklogService  extends  CrudService2<BacklogDao,Backlog> {
@Autowired
private BacklogDao backlogDao;
	public List<Backlog> findOrderTaskPackageByManagerId(Integer id, String orderTaskpackageStatus80,
			String orderTaskpackageStatus95, String orderTaskpackageStatus110, String orderTaskpackageStatus130) {
		// TODO Auto-generated method stub
		return backlogDao.findOrderTaskPackageByManagerId(id,orderTaskpackageStatus80,orderTaskpackageStatus95,orderTaskpackageStatus110,orderTaskpackageStatus130);
	}
	public List<Backlog> findApplyMaterial(Integer id) {
		/*//查询项目经理所有的订单ID 过滤到没有点确认开工的
		List<String> allId = backlogDao.findAllOrderIDByID(id);
		//查询申请采购单的ID
		List<String> applyMaterialId = backlogDao.findNoApplyMaterialId(allId);
		//查询延期辅料的采购单
		List<Backlog> list2 = backlogDao.findNoApplyMaterialAll(allId);
		List<Backlog> list = null;
		//如果有
		if(allId.size()!=applyMaterialId.size()){
			//取差集
			allId.removeAll(applyMaterialId);
			list = backlogDao.findNoApplyMaterial(allId); 
		}
		if(list!=null){
			list2.addAll(list);
		} 
		//查询
		
		
		return list2;*/
		
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
