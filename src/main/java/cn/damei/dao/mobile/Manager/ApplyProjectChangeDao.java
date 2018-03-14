package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.common.utils.phoneMessage.MessageEmployeePhoneAndId;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.entity.mobile.Manager.ProjectItemType;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface ApplyProjectChangeDao {
	
	/**
	 * 订单
	 * @param managerId
	 * @return
	 */
	public List<ApplyProjectChangeEntity> findOrderList(Integer managerId);
	
	//是否有申请记录
	public Integer findOrderCount(Integer orderId);
	
	
	/**
	 * 查询所有增项,套餐外, 状态启用的 施工分类和施工项
	 * @return
	 */
	public List<ProjectItem>findAddItemList(Integer typeId, String storeId);
	
	
	//2017-1-16 日更改需求 
	public List<ProjectItemType> findAllProjectTypes();
	
	
	/**
	 * 减项的 套餐内外, 根据参数决定
	 * @param innerOrOuter
	 * @return
	 */
	public List<ProjectItem>findMinusInnerItemList(ProjectItem item);
	
	
	/**
	 * 根据 施工项查询施工项详情
	 * @param itemId
	 * @return
	 */
	public ProjectItem  findItemDetailById(String itemId,String storeId);
	
	//编号
	public ReCheckCode  getCode();
	public void updateCode(ReCheckCode code);
	public void saveCode();
	
	//保存变更单和 变更项
	public void  saveProjectBill(ApplyProjectChangeEntity entity);
	public void saveProjectItem(ProjectItem  item);
	//更新价格
	public void updateProjectBillMoney(ApplyProjectChangeEntity entity);
	
	//查询申请记录
	public List<ApplyProjectChangeEntity>findProjectApplyByOrderId(Integer orderId);
	
	
	//变更单详情
	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId);
	public ApplyProjectChangeEntity findChangeBillDetailByIdAndStatus(Integer billId);
	
	//变更项详情
	public List<ProjectItem>findChangeItemByChangeBillId(Integer billId);
	
	//更新之前的删除
	public void deleteChangeBillById(Integer  billId);
	public void deleteChangeItemByBillId(Integer  billId);
	
	public Integer findCountByItemId(Integer itemId);
	
	public String findCustomerNameByprojectChangeId(Integer projectId);
	
	/**
	 * 根据订单id 查询发送短信需要的信息, 客户信息和设计师信息
	 * @param orderId
	 * @return
	 */
	public MessageEmployeePhoneAndId  findMessageInfoByOrderId(Integer orderId);
	/**
	 * 更新图片的业务标识ID
	 * @param projectChangeId
	 * @param changeId
	 */
	public void updatePicBusinessId(Integer projectChangeId, String changeId);

    List<ProjectItem> findProjectItemDetailById(@Param("map")Map map);
}
