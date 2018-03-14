package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.entity.mobile.home.BizProjectChangeBill;

@MyBatisDao
public interface ProjectChangeDao {


	public List<ApplyProjectChangeEntity> findChangeList(String phone);
		

	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId);
	

	public List<ProjectItem> findChangeItemByChangeBillId(Integer billId);


	public void updateChangeBill(BizProjectChangeBill bizProjectChangeBill);

	
	
}
