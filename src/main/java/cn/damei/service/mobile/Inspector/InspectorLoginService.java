package cn.damei.service.mobile.Inspector;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Inspector.InspectorLoginDao;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;


@Service
@Transactional(readOnly=true)
public class InspectorLoginService  extends CrudService2<InspectorLoginDao, Inspector>{
	
	@Autowired
	private HomeReportDao homeReportDao;
	
	public Inspector selectInspectorByPhone(String phone) {
			
			return dao.selectInspectorByPhone(phone);
		}
	
	public int findCountByManagerName(Integer id) {
		return dao.findCount(id);
	}
	
	public int findCountByManagerNameAndOrderStatus(Integer id) {
		return dao.findBuildingCount(id);
	}
	
	public Integer	findInspectReport(Integer inspectorId){
		
		return dao.findInspectReport(inspectorId);
	}
	

	public Integer findEvalCount(Inspector inspector) {
		return dao.findEvalCount(inspector);
	}
	public Integer isLeader(Integer id){
		
		return  dao.isLeader(id);
	}


	public Integer findView(Inspector inspector) {
		return dao.findView(inspector);
	}


	@Transactional(readOnly=false)
	public void insertView(Inspector inspector) {
		Date date = new Date();
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessType("333");
		viewLog.setBusinessViewDatetime(date);
		viewLog.setBusinessViewerEmployeeId(inspector.getId());
		viewLog.setBusinessViewerOnlyMark(inspector.getPhone());
		viewLog.setCreateDate(date);
		viewLog.setUpdateDate(date);
		viewLog.setDelFlag("0");
		
		homeReportDao.insertView(viewLog);
		
	}

	public Inspector selectInspectorByPhone1(String username, String string) {

		return dao.selectInspectorByPhone1(username,string);
	}

}
