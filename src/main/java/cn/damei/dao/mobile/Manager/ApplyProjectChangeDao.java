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
	

	public List<ApplyProjectChangeEntity> findOrderList(Integer managerId);
	

	public Integer findOrderCount(Integer orderId);
	
	

	public List<ProjectItem>findAddItemList(Integer typeId, String storeId);
	
	

	public List<ProjectItemType> findAllProjectTypes();
	
	

	public List<ProjectItem>findMinusInnerItemList(ProjectItem item);
	
	

	public ProjectItem  findItemDetailById(String itemId,String storeId);
	

	public ReCheckCode  getCode();
	public void updateCode(ReCheckCode code);
	public void saveCode();
	

	public void  saveProjectBill(ApplyProjectChangeEntity entity);
	public void saveProjectItem(ProjectItem  item);

	public void updateProjectBillMoney(ApplyProjectChangeEntity entity);
	

	public List<ApplyProjectChangeEntity>findProjectApplyByOrderId(Integer orderId);
	
	

	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId);
	public ApplyProjectChangeEntity findChangeBillDetailByIdAndStatus(Integer billId);
	

	public List<ProjectItem>findChangeItemByChangeBillId(Integer billId);
	

	public void deleteChangeBillById(Integer  billId);
	public void deleteChangeItemByBillId(Integer  billId);
	
	public Integer findCountByItemId(Integer itemId);
	
	public String findCustomerNameByprojectChangeId(Integer projectId);
	

	public MessageEmployeePhoneAndId  findMessageInfoByOrderId(Integer orderId);

	public void updatePicBusinessId(Integer projectChangeId, String changeId);

    List<ProjectItem> findProjectItemDetailById(@Param("map")Map map);
}
