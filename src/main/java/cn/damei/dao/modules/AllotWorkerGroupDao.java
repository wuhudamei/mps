package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.WorkgroupVo;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月12日 下午3:00:36 
* 类说明 
*/
@MyBatisDao
public interface AllotWorkerGroupDao extends CrudDao<WorkgroupVo> {

	/**
	 * 根据任务包id查询任务包
	 * @param id
	 * @return
	 */
	public OrderTaskpackage  findTargetPackageById(Integer id);
	
	/**
	 * 根据工人组id  去 工人关系表查询 有多少工人
	 * @param id
	 * @return
	 */
	public Integer findCountByWorkerId(Integer id);
	
	/**
	 * 根据组长id  获取组长名字
	 * @return
	 */
	public  String  findTeamLeaderNameById(Integer id);
	
	/**
	 * 根据组长id,查询组长手机号
	 * @return
	 */
	public String findTeamLeaderPhoneById(Integer id);
	/**
	 * 根据工程部id , 查询工程部名称
	 * @return
	 */
	public String findengineerById(String id);
	
	/**
	 * 保存任务包的状态
	 * @param pack
	 *
	 */
	public void  updatePackage(OrderTaskpackage pack);
	
	/**
	 * 查找所有任务包
	 * 条件: 有分配过的工人组id
	 * @return
	 */
	public List<OrderTaskpackage> findAllPackageWhomHasEmpGroup( List<WorkgroupVo> list);
	
	/**
	 * 根据工人组id查询工人组
	 * @param workGroupId
	 * @return
	 */
	public WorkgroupVo  getWorkgroupById(Integer workGroupId);
	
	/**
	 * 发短信所需要的信息
	 * @param packId
	 * @return
	 */
	public  OrderTaskpackage	sendMessagetoWorker(Integer packId);
	
	public List<WorkgroupVo> findWorkerGroupInfoById( List<WorkgroupVo>  workgroupVoList);

	public List<WorkgroupVo> findPageSpecialListNew(WorkgroupVo workgroupVo);
}
