package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageSettlementDetail;

import java.util.Map;

/**
 * 项目经理端
 * 现场交底
 * @author qww
 * 2016/10/17
 */
@MyBatisDao
public interface BizOrderTaskpackageSettlementDetailDao extends CrudDao2<BizOrderTaskpackageSettlementDetail>{

	/**
	 * 修改员工分配薪酬
	 * @param detail
	 * @return
	 */
	public int updateSettlementDetail(BizOrderTaskpackageSettlementDetail detail);

	public BizOrderTaskpackageSettlementDetail querySettlementDetailByTaskId(Map<String, Object> map);
}
