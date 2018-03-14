/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.GroupSendMessage;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.TeamLeaderInfo;

/**
 * 派工管理DAO接口
 * @author wyb
 * @version 2016-09-12
 */
@MyBatisDao
public interface OrderTaskpackageDao extends CrudDao<OrderTaskpackage> {

	/**
	 * 根据工人组id 得到组长id  返回 组长的姓名,手机,及照片  
	 * @param employeeGroupId
	 * @return
	 */
	public TeamLeaderInfo  findTeamLeaderInfoByEmployeeGroupId(String employeeGroupId);

	/**
	 * 根据工人组id 查询可接任务包id
	 * @param employeeGroupId
	 * @return
	 */
	public  String findTaskPackageByemployeeGroupId(String employeeGroupId);

	
	/**
	 * 根据任务包id查询任务包名称
	 * @param packAgeId
	 * @return
	 */
	public String findPackageNameById(String packAgeId);
	
	
	/**
	 * 根据工人组id查询组内有多少工人
	 * @param packAgeId
	 * @return
	 */
	public Integer findCountByWorkerId(String packAgeId);
	
	
	/**
	 * 根据任务包的状态查询任务包
	 */
	public List<OrderTaskpackage> findListMy(OrderTaskpackage orderTaskpackage);

	public boolean insertTaskpackageByOrder(OrderTaskpackage orderTaskpackage);

	/**
	 * 查询是否存在订单任务包
	 */
	public List<OrderTaskpackage> queryListByOrderTaskpackage(String orderId, String storeId,String projectMode);

	/**
	 */
	public List<OrderTaskpackage> getByOrderId(String orderId);
	
	public OrderTaskpackage getByOrderIdB(String orderId,String packageCode);

	//通过订单id查询订单编号
	public String findOrderNumber(Integer id);

	public List<GroupSendMessage> getByNewDate(String beforeDate);

	public List<OrderTaskpackage> getByOrderIDList(Integer valueOf);

	public void insertTaskpackage(OrderTaskpackage taskpackage);

	public OrderTaskpackage findPackageByCode(String code);

	public void insertProcedure(OrderTaskpackGenVo vo);

	public void insertProcedureList(List<OrderTaskpackGenVo> list);

	public int getUnOrderTaskPackage(OrderTaskpackage orderTaskpackage);

	public List<OrderTaskpackage> findSpecialPageMy(OrderTaskpackage orderTaskpackage);
}