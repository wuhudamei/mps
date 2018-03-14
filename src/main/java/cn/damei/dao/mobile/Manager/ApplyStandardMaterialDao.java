package cn.damei.dao.mobile.Manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ApplyMaterialsStandardReceiveDetail;
import cn.damei.entity.mobile.Manager.ApplyStandardMaterial;
import cn.damei.entity.mobile.Manager.BizMaterialsStandard;
import cn.damei.entity.mobile.Manager.BizMaterialsStandardReceiveBillApply;
import cn.damei.entity.mobile.Manager.Seiralnum;
@MyBatisDao
public interface ApplyStandardMaterialDao extends CrudDao2<ApplyStandardMaterial>{

	public List<ApplyStandardMaterial> findOrderListById(ApplyStandardMaterial am);

	public List<BizMaterialsStandardReceiveBillApply> findMaterialStandardBillByOrderId(@Param("orderId")String orderId,@Param("receiveBillType")String receiveBillType);

	public List<BizMaterialsStandard> findBizMaterialsStandardList(@Param("storeid")String storeid,@Param("orderId")String orderId);

	public BizMaterialsStandard findStandardPriceById(String id);

	public Integer saveMaterialsStandardReceiveBill(BizMaterialsStandardReceiveBillApply bsr);

	public void insertMaterialBillVO(List<ApplyMaterialsStandardReceiveDetail> listBD);

	public ApplyStandardMaterial findApplyStandardMaterialByOrderId(String orderId);

	public List<ApplyMaterialsStandardReceiveDetail> findApplyMaterialsStandardReceiveDetailById(String id);

	public BizMaterialsStandardReceiveBillApply findBizMaterialsStandardReceiveBillApplyByid(String id);

	public String findStoreIdByEmployeeId(Integer managerId);

	public Seiralnum findSeiralnum(int i);

	public void saveSeiralnum(Seiralnum seiralnum);

	List<String> getRuleCodeNumber(String materialsStandardId);

	String getOrderArea(String orderId);
	
}
