
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBroadcastBill;


@MyBatisDao
public interface BizBroadcastBillDao extends CrudDao<BizBroadcastBill> {
	
	
	
	public List<String> findPackageTempleteList();
	public List<BizBroadcastBill>findBroadCastInfoAndPic(Integer broadcastId);
}