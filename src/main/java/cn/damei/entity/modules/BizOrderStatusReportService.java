package cn.damei.entity.modules;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderStatusReportDao;
import cn.damei.entity.modules.BizOrderStatusReport;

/**
 * 大统计表-大阶段(只查询订单状态是100至400 的项目)
 * @author llp
 * @version 2016-11-29
 */
@Service
@Transactional(readOnly = true)
public class BizOrderStatusReportService extends CrudService2<BizOrderStatusReportDao, BizOrderStatusReport>{

	public BizOrderStatusReport get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderStatusReport> findList(BizOrderStatusReport bizOrderStatusReport) {
		return super.findList(bizOrderStatusReport);
	}

	public List<BizOrderStatusReport> getByStoreID(String storeId) {
		// TODO Auto-generated method stub
		return dao.getByStoreID(storeId);
	}
}
