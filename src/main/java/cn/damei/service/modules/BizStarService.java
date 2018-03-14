
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizStar;
import cn.damei.dao.modules.BizStarDao;


@Service
@Transactional(readOnly = true)
public class BizStarService extends CrudService<BizStarDao, BizStar> {
	@Autowired
	private BizStarDao bizStarDao;
	
	public BizStar get(String id) {
		return super.get(id);
	}
	
	public List<BizStar> findList(BizStar bizStar) {
		return super.findList(bizStar);
	}
	

	public List<BizStar> findStarByStoreId(String storeId){
		return bizStarDao.findStarByStoreId(storeId);
	}
	
	
	public Page<BizStar> findPage(Page<BizStar> page, BizStar bizStar) {
		return super.findPage(page, bizStar);
	}
	
	@Transactional(readOnly = false)
	public void save(BizStar bizStar) {
		super.save(bizStar);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizStar bizStar) {
		super.delete(bizStar);
	}
	
}