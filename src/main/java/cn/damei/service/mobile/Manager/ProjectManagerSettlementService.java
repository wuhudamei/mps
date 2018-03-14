package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.ProjectManagerSettlementDao;
import cn.damei.entity.mobile.Manager.ProjectManagerSettlement;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;

@Service
@Transactional(readOnly=false)
public class ProjectManagerSettlementService extends CrudService2<ProjectManagerSettlementDao, ProjectManagerSettlement>{

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;

	public void updateManagerSettlement(ProjectManagerSettlement projectManagerSettlement, Manager manager) {
		projectManagerSettlement.preInsert();
		String status = projectManagerSettlement.getStatus();
		if(status.equals("40")){
			projectManagerSettlement.setStatusDescribe("项目经理同意结算金额");

	          BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
	          statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3800);
	          statusLog.setBusinessOnlyMarkInt(projectManagerSettlement.getId());
	          statusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_40);
	          statusLog.setStatusDatetime(new Date());
	         
	          statusLog.setBusinessEmployeeId(manager.getId());
	         
	          statusLog.setBusinessRemarks("项目经理同意结算金额");
	          statusLog.preInsert();
	          bizBusinessStatusLogDao.insert(statusLog);
			
		}
		if(status.equals("45")){
			projectManagerSettlement.setStatusDescribe("项目经理拒绝结算金额");

	          BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
	          statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3500);
	          statusLog.setBusinessOnlyMarkInt(projectManagerSettlement.getId());
	          statusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_45);
	          statusLog.setStatusDatetime(new Date());
	          statusLog.setBusinessEmployeeId(manager.getId());
	          statusLog.setBusinessRemarks("项目经理拒绝结算金额");
	          statusLog.setRemarks(projectManagerSettlement.getRemarks());
	          statusLog.preInsert();
	          bizBusinessStatusLogDao.insert(statusLog);
			
		}
		
		dao.updateManagerSettlement(projectManagerSettlement);
	}

	public ProjectManagerSettlement findSettlement(String orderId, int i) {

		return dao.findSettlement(orderId,i);
	}

	public List<ProjectManagerSettlement> findSettlementEndList(ProjectManagerSettlement projectManagerSettlement) {

		return dao.findSettlementEndList(projectManagerSettlement);
	}

	public ProjectManagerSettlement findSettlementEndDetails(ProjectManagerSettlement projectManagerSettlement) {

		return dao.findSettlementEndDetails(projectManagerSettlement);
	}

	

}
