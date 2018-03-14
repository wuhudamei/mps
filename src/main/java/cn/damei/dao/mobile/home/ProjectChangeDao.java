package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.entity.mobile.home.BizProjectChangeBill;
/**
 * 客户审核变更单
 * @author Administrator
 *
 */
@MyBatisDao
public interface ProjectChangeDao {

	//根据客户手机号查询变更单
	public List<ApplyProjectChangeEntity> findChangeList(String phone);
		
	//变更单详情
	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId);
	
	//变更项详情
	public List<ProjectItem> findChangeItemByChangeBillId(Integer billId);

	//客户审核
	public void updateChangeBill(BizProjectChangeBill bizProjectChangeBill);

	
	
}
