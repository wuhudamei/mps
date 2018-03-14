
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallItemReportDao;
import cn.damei.entity.modules.BizOrderInstallItemReport;


@Service
@Transactional(readOnly = true)
public class BizOrderInstallItemReportService extends CrudService2<BizOrderInstallItemReportDao, BizOrderInstallItemReport> {

	public BizOrderInstallItemReport get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallItemReport> findList(BizOrderInstallItemReport bizOrderInstallItemReport) {
		return super.findList(bizOrderInstallItemReport);
	}
	
	public Page<BizOrderInstallItemReport> findPage(Page<BizOrderInstallItemReport> page, BizOrderInstallItemReport bizOrderInstallItemReport) {
		return super.findPage(page, bizOrderInstallItemReport);
	}
	


	
}