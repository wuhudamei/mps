package cn.damei.service.mobile.home;

import java.util.List;

import cn.damei.dao.mobile.home.ProjectProgressDao;
import cn.damei.entity.mobile.home.ProjectProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class ProjectProgressService {

	@Autowired
	private ProjectProgressDao dao;
	
	public List<ProjectProgressVo>getOrderListByCustomerPhone(String customerPhone){
		return dao.getOrderListByCustomerPhone(customerPhone);
	}
	
	public ProjectProgressVo   getConfirmStartInfoByOrderId(ProjectProgressVo  projectProgressVo){
		if(null!=projectProgressVo&&null!=projectProgressVo.getOrderId()){
		
		return dao.getConfirmStartInfoByOrderId(projectProgressVo);
		 
		 
		 
		 
		}else{
			
			return null;
		}
	}
	
	@Transactional(readOnly=false)
	public   void   insertLogForProjectProgress (ProjectProgressVo  projectProgressVo){
		
		
		dao.insertLogForProjectProgress(projectProgressVo);
		
	}
	public Integer  findLog (ProjectProgressVo  projectProgressVo){
		
		return dao.findLog(projectProgressVo);
	}
	
}
