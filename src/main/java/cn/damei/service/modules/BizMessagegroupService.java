
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.dao.modules.BizMessagegroupDao;


@Service
@Transactional(readOnly = true)
public class BizMessagegroupService extends CrudService<BizMessagegroupDao, BizMessagegroup> {

	@Autowired
	private BizMessagegroupDao bizMessagegroupDao;
	
	public BizMessagegroup get(String id) {
		return super.get(id);
	}
	
	public List<BizMessagegroup> findList(BizMessagegroup bizMessagegroup) {
		return super.findList(bizMessagegroup);
	}
	
	public Page<BizMessagegroup> findPage(Page<BizMessagegroup> page, BizMessagegroup bizMessagegroup) {
		return super.findPage(page, bizMessagegroup);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMessagegroup bizMessagegroup) {
		super.save(bizMessagegroup);
	}
	
	@Transactional(readOnly = false)
	public void update(BizMessagegroup bizMessagegroup) {
		super.save(bizMessagegroup);
	}
	
	
	@Transactional(readOnly = false)
	public void delete(BizMessagegroup bizMessagegroup) {
		super.delete(bizMessagegroup);
	}


	public BizMessagegroup getByStoreId(String storeId,String messageGroupType) {
		BizMessagegroup bizMessagegroup = bizMessagegroupDao.getByStoreId(storeId,messageGroupType);
		return bizMessagegroup;
	}
	
}