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
	/**
	 * 根据经理ID查询订单的信息
	 * @param managerId
	 * @param villageAndName 
	 * @return
	 */
	public List<ApplyStandardMaterial> findOrderListById(ApplyStandardMaterial am);
	/**
	 * 根据订单的ID查询标化材料的订单
	 * @param orderId
	 * @return
	 */
	public List<BizMaterialsStandardReceiveBillApply> findMaterialStandardBillByOrderId(@Param("orderId")String orderId,@Param("receiveBillType")String receiveBillType);
	/**
	 * 查询标化材料列表
	 * @param storeid 
	 * @return
	 */
	public List<BizMaterialsStandard> findBizMaterialsStandardList(@Param("storeid")String storeid,@Param("orderId")String orderId);
	/**
	 * 根据商品的ID查询单价
	 * @param id
	 * @return
	 */
	public BizMaterialsStandard findStandardPriceById(String id);
	/**
	 * 保存标化材料申请记录
	 * @param bsr
	 */
	public Integer saveMaterialsStandardReceiveBill(BizMaterialsStandardReceiveBillApply bsr);
	/**
	 * 批量插入 ApplyMaterialsStandardReceiveDetail
	 * @param listBD
	 */
	public void insertMaterialBillVO(List<ApplyMaterialsStandardReceiveDetail> listBD);
	/**
	 * 根据订单的ID查询订单的信息
	 * @param orderId
	 * @return
	 */
	public ApplyStandardMaterial findApplyStandardMaterialByOrderId(String orderId);
	/**
	 * 根据申请记录的详情查询标化材料的信息
	 * @param id
	 * @return
	 */
	public List<ApplyMaterialsStandardReceiveDetail> findApplyMaterialsStandardReceiveDetailById(String id);
	/**
	 * 根据领取单ID查询领取单信息
	 * @param id
	 * @return
	 */
	public BizMaterialsStandardReceiveBillApply findBizMaterialsStandardReceiveBillApplyByid(String id);
	/**
	 * 查询门店id
	 * @param managerId
	 * @return
	 */
	public String findStoreIdByEmployeeId(Integer managerId);
	/**
	 * 查询流水表
	 * @param i
	 * @return
	 */
	public Seiralnum findSeiralnum(int i);
	/**
	 * 更新流水表
	 * @param seiralnum
	 */
	public void saveSeiralnum(Seiralnum seiralnum);
	//通過 materialsStandardId 获取 计算面积的code
	List<String> getRuleCodeNumber(String materialsStandardId);
	//获取 订单 面积
	String getOrderArea(String orderId);
	
}
