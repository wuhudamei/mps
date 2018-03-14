
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.CheckDetailsDao;
import cn.damei.entity.modules.CheckDetails;
import cn.damei.entity.modules.BizQcBill;


@Service
@Transactional(readOnly = true)
public class CheckDetailsService extends CrudService<CheckDetailsDao, CheckDetails> {

	public CheckDetails get(String id) {
		return super.get(id);
	}
	
	public List<CheckDetails> findList(CheckDetails checkDetails) {
		return super.findList(checkDetails);
	}
	
	public Page<CheckDetails> findPage(Page<CheckDetails> page, CheckDetails checkDetails) {
		return super.findPage(page, checkDetails);
	}


	public List<BizQcBill> detailsList(Integer orderId) {
		return dao.detailsList(orderId);
	}
	
}