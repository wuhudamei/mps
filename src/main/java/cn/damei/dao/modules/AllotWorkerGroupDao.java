package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.WorkgroupVo;


@MyBatisDao
public interface AllotWorkerGroupDao extends CrudDao<WorkgroupVo> {


	public OrderTaskpackage  findTargetPackageById(Integer id);
	

	public Integer findCountByWorkerId(Integer id);
	

	public  String  findTeamLeaderNameById(Integer id);
	

	public String findTeamLeaderPhoneById(Integer id);

	public String findengineerById(String id);
	

	public void  updatePackage(OrderTaskpackage pack);
	

	public List<OrderTaskpackage> findAllPackageWhomHasEmpGroup( List<WorkgroupVo> list);
	

	public WorkgroupVo  getWorkgroupById(Integer workGroupId);
	

	public  OrderTaskpackage	sendMessagetoWorker(Integer packId);
	
	public List<WorkgroupVo> findWorkerGroupInfoById( List<WorkgroupVo>  workgroupVoList);

	public List<WorkgroupVo> findPageSpecialListNew(WorkgroupVo workgroupVo);
}
