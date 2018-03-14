package cn.damei.service.mobile.Worker;

import java.util.Date;
import java.util.List;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.service.modules.ReturnOrderLogBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.WorkerBizOrderReportDao;
import cn.damei.entity.modules.BizOrderReport;

/**
 * 工人返单上报Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class WorkerBizOrderReportService extends CrudService2<WorkerBizOrderReportDao, BizOrderReport>{

	//log及校验返单的service
	@Autowired
	private ReturnOrderLogBusinessService reportLogService;


	public BizOrderReport get(Integer id){
		return super.get(id);
	}
	
	public Integer getBizOrderReportByCustomerPhone(String customerPhone){
		return dao.getBizOrderReportByCustomerPhone(customerPhone);
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
	public void save(BizOrderReport bizOrderReport,Worker worker){
		bizOrderReport.setReporterEmployeeId(worker.getId());
		bizOrderReport.setReporterName(worker.getRealname());
		bizOrderReport.setReporterPhone(worker.getPhone());
		bizOrderReport.setStoreId(Integer.valueOf(worker.getStoreid()));

		bizOrderReport.setReportDatetime(new Date());
		bizOrderReport.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_10);// 返单上报
		bizOrderReport.setReporterType(BizOrderReportConstantUtil.REPORT_TYPE_3);// 工人
		bizOrderReport.setReportSourceType(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_3);// 工人APP
		super.save(bizOrderReport);
		BizOrderReport checkedReport=reportLogService.checkOrderReportIsExist(bizOrderReport);
		super.save(checkedReport);

	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderReport bizOrderReport) {
		super.delete(bizOrderReport);
	}
}
