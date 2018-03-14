package cn.damei.service.mobile.home;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.dao.mobile.home.ProjectChangeDao;
import cn.damei.entity.mobile.home.BizProjectChangeBill;

@Service
@Transactional(readOnly=true)
public class ProjectChangeService {

	
	@Autowired
	private ProjectChangeDao dao;
	

	public List<ApplyProjectChangeEntity> findChangeList(String phone) {
		return dao.findChangeList(phone);
	}
	

	public ApplyProjectChangeEntity findChangeBillDetailById(Integer billId){
		return 	dao.findChangeBillDetailById( billId);
	}
	

	public List<ProjectItem>findChangeItemByChangeBillId(Integer billId){
		return 	dao.findChangeItemByChangeBillId(billId);
	}


	@Transactional(readOnly=false)
	public void updateChangeBill(String projectChangeId, String reason,String status) {
		BizProjectChangeBill bizProjectChangeBill = new BizProjectChangeBill();
		bizProjectChangeBill.setProjectChangeId(Integer.valueOf(projectChangeId));
		bizProjectChangeBill.setStatus(status);
		bizProjectChangeBill.setCheckDate(new Date());
		bizProjectChangeBill.setCheckEmployeeId(Integer.valueOf("0"));
		bizProjectChangeBill.setCheckWords(reason);
		dao.updateChangeBill(bizProjectChangeBill);
	}
	

	
	
}
