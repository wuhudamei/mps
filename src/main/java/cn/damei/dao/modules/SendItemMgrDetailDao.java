package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.SendItemMgrDetail;

/**
 * @ClassName: SendItemMgrDetailDao 
 * @Description: 派项目经理明细DAO
 * @author huhanwei 
 * @date 2017年6月21日 下午2:36:14
 */
@MyBatisDao
public interface SendItemMgrDetailDao extends CrudDao2<SendItemMgrDetail> {

	public List<SendItemMgrDetail> findListToExport(SendItemMgrDetail sendItemMgrDetail);

	public List<SendItemMgrDetail> findDispatchDetailToExport(SendItemMgrDetail sendItemMgrDetail);

	public List<SendItemMgrDetail> findManagerCount();
	
}
