package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyRecheckEntity;

@MyBatisDao
public interface ApplyRecheckDao {

	
	
	/**
	 * 根据项目经理id查询订单下的质检单的复检单
	 * @param managerId
	 * @return
	 */
	public List<ApplyRecheckEntity> findRecheckList(Integer managerId);
	
	
	
	/**
	 * 根据复检单id 查询不合格的检查项
	 * @param reCheckId
	 * @return
	 */
	public List<ApplyRecheckEntity> selectCheckItemByRecheckId(Integer reCheckId);
	
	
	
	/**
	 * 保存经理申请复检时的照片
	 * @param pic
	 */
	public void savePic(ReportCheckDetailsPic pic);
	
	
	/**
	 * 更新复检单  status  申请人,期望日期,等
	 *
	 * @param entity
	 */
	public void updateRecheckStatus(ApplyRecheckEntity  entity);
	
}
