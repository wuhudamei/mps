package cn.damei.dao.modules;

import java.util.List;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardRecords;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillApply;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface BizMaterialsStandardReceiveBillApplyDao  extends CrudDao2<BizMaterialsStandardReceiveBillApply>{
	/**
	 * 根据id更新标化材料单
	 * @param bizMaterialsStandardReceiveBillApply
	 */
	public void materialsApplyBillById(BizMaterialsStandardReceiveBillApply bizMaterialsStandardReceiveBillApply);
	/**
	 * 根据id查询标化详情材料单
	 * @param ba
	 * @return
	 */
	public List<BizMaterialsStandardReceiveBillApply> materialsApplyBillVoById(BizMaterialsStandardReceiveBillApply ba);
	/**
	 * 根据id查询标化材料单
	 * @param billId
	 * @return
	 */
	public List<BizMaterialsStandardRecords> findApplyMaterialsStandardReceiveDetailById(String billId);
	//根据 billId 修改 detail表的 领取快照

	/**
	 * 根据订单 id 查询下面 （已领取）的订单项
	 * @param orderId
	 * @return
	 */
	List<BizMaterialsStandardReceiveBillApply> findMaterialsApplyBillByOrderId(@Param("orderId") String orderId, @Param("receiveBillType")String receiveBillType);
	
	
}
