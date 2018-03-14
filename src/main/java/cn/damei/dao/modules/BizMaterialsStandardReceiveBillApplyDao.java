package cn.damei.dao.modules;

import java.util.List;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardRecords;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillApply;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface BizMaterialsStandardReceiveBillApplyDao  extends CrudDao2<BizMaterialsStandardReceiveBillApply>{

	public void materialsApplyBillById(BizMaterialsStandardReceiveBillApply bizMaterialsStandardReceiveBillApply);

	public List<BizMaterialsStandardReceiveBillApply> materialsApplyBillVoById(BizMaterialsStandardReceiveBillApply ba);

	public List<BizMaterialsStandardRecords> findApplyMaterialsStandardReceiveDetailById(String billId);



	List<BizMaterialsStandardReceiveBillApply> findMaterialsApplyBillByOrderId(@Param("orderId") String orderId, @Param("receiveBillType")String receiveBillType);
	
	
}
