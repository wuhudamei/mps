package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizDiscloseDao;
import cn.damei.entity.modules.BizDisclose;

/**
 * 订单交底
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizDiscloseService extends CrudService2<BizDiscloseDao, BizDisclose>{
	
	@Autowired
	private BizDiscloseDao bizDiscloseDao;
	
	public BizDisclose get(Integer id) {
		return super.get(id);
	}
	
	public List<BizDisclose> findList(BizDisclose bizDisclose) {
		return super.findList(bizDisclose);
	}
	
	public Page<BizDisclose> findPage(Page<BizDisclose> page, BizDisclose bizDisclose) {
		return super.findPage(page, bizDisclose);
	}

	/**
	 * @return List<BizOrderDisclose>
	 */
	public List<BizDisclose> getByList() {
		return bizDiscloseDao.getByList();
	}

}
