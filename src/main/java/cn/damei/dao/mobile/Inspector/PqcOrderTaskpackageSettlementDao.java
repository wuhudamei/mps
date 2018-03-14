package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageSettlement;

/** 
* @author qww 
* @version 创建时间：2016年9月19日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface PqcOrderTaskpackageSettlementDao  extends CrudDao2<PqcOrderTaskpackageSettlement>{
	
	/**
	 * 根据任务包id查询结算单
	 * @param orderTaskpackageId
	 * @return
	 */
	public PqcOrderTaskpackageSettlement querySettlementByOrderTaskpackageId(Integer orderTaskpackageId);
}