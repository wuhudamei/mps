package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.modules.ReturnOrderLogBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ManagerBizOrderReportDao;
import cn.damei.entity.modules.BizOrderReport;


@Service
@Transactional(readOnly = true)
public class ManagerBizOrderReportService extends CrudService2<ManagerBizOrderReportDao, BizOrderReport>{



	@Autowired
	private ReturnOrderLogBusinessService reportLogService;



	public BizOrderReport get(Integer id){
		return super.get(id);
	}
	
	public Integer  getPhoneCountByCustomerPhone(String customerPhone){
		return dao.getPhoneCountByCustomerPhone(customerPhone);
	}
	
	public List<BizOrderReport> findList(BizOrderReport bizOrderReport){
		return super.findList(bizOrderReport);
	}
	
	public List<BizOrderReport> queryOrderReportByParam(BizOrderReport bizOrderReport){
		return dao.queryOrderReportByParam(bizOrderReport);
	}
	
	public Page<BizOrderReport> findPage(Page<BizOrderReport> page, BizOrderReport bizOrderReport) {
		return super.findPage(page, bizOrderReport);
	}



	@Transactional(readOnly = false)
	public void save(BizOrderReport bizOrderReport,Manager manager){
		bizOrderReport.setReporterEmployeeId(manager.getId());
		bizOrderReport.setReporterName(manager.getRealname());
		bizOrderReport.setReporterPhone(manager.getPhone());
		bizOrderReport.setStoreId(Integer.valueOf(manager.getStoreid()));

		bizOrderReport.setReportDatetime(new Date());
		bizOrderReport.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_10);
		bizOrderReport.setReporterType(BizOrderReportConstantUtil.REPORT_TYPE_1);
		bizOrderReport.setReportSourceType(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_1);


		super.save(bizOrderReport);
		BizOrderReport checkedReport=reportLogService.checkOrderReportIsExist(bizOrderReport);
		super.save(checkedReport);

	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderReport bizOrderReport) {
		super.delete(bizOrderReport);
	}
}
