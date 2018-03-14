package cn.damei.entity.modules;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderConstructionReportDao;
import cn.damei.entity.modules.BizOrderConstructionReport;


@Service
@Transactional(readOnly = true)
public class BizOrderConstructionReportService extends CrudService2<BizOrderConstructionReportDao, BizOrderConstructionReport>{

	public BizOrderConstructionReport get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderConstructionReport> findList(BizOrderConstructionReport bizOrderConstructionReport) {
		return super.findList(bizOrderConstructionReport);
	}

	public List<BizOrderConstructionReport> getByStoreList(String storeID) {

		return dao.getByStoreList(storeID);
	}
}
