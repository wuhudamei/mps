package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackageSettlementVo;

@MyBatisDao
public interface BizOrderTaskpackageSettlementVoDao extends CrudDao2<BizOrderTaskpackageSettlementVo>{

	void updateRefusedReason(Integer orderTaskpackageId, String reason, Date date, String status);

	/**
	 * 查询结算单修改列表
	 * @param vo
	 * @return
	 */
	public List<BizOrderTaskpackageSettlementVo> findSettlementList(BizOrderTaskpackageSettlementVo vo);

	public List<BizOrderTaskpackageSettlementVo> findSettlementAllList(BizOrderTaskpackageSettlementVo vo);

	Date queryConfirmSalaryTime(Integer id);

	/**
	 * 查询超定额任务包列表
	 * @param vo
	 * @return
	 */
	public List<BizOrderTaskpackageSettlementVo> findSettlementTaskList(BizOrderTaskpackageSettlementVo vo);

	public BizOrderTaskpackageSettlementVo queryEntityByOrderTaskpackageId(Integer orderTaskpackageId);
	
	public BizOrderTaskpackageSettlementVo querySettlementByOrderTaskpackageId(Integer orderTaskpackageId);
	
	public List<BizOrderTaskpackageSettlementVo> querySettleCancel(BizOrderTaskpackageSettlementVo vo);
	
	
}
