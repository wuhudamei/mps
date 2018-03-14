
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.GroupSendMessage;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.TeamLeaderInfo;


@MyBatisDao
public interface OrderTaskpackageDao extends CrudDao<OrderTaskpackage> {


	public TeamLeaderInfo  findTeamLeaderInfoByEmployeeGroupId(String employeeGroupId);


	public  String findTaskPackageByemployeeGroupId(String employeeGroupId);

	

	public String findPackageNameById(String packAgeId);
	
	

	public Integer findCountByWorkerId(String packAgeId);
	
	

	public List<OrderTaskpackage> findListMy(OrderTaskpackage orderTaskpackage);

	public boolean insertTaskpackageByOrder(OrderTaskpackage orderTaskpackage);


	public List<OrderTaskpackage> queryListByOrderTaskpackage(String orderId, String storeId,String projectMode);


	public List<OrderTaskpackage> getByOrderId(String orderId);
	
	public OrderTaskpackage getByOrderIdB(String orderId,String packageCode);


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