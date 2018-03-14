package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.ProjectProgressVo;

@MyBatisDao
public interface ProjectProgressDao {

	public List<ProjectProgressVo>getOrderListByCustomerPhone(String customerPhone);
	
	public ProjectProgressVo   getConfirmStartInfoByOrderId(ProjectProgressVo  projectProgressVo);
	
	public   void   insertLogForProjectProgress (ProjectProgressVo  projectProgressVo);
	public Integer  findLog (ProjectProgressVo  projectProgressVo);
}
