
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizSynData;
import cn.damei.dao.modules.BizSynDataDao;


@Service
@Transactional(readOnly = true)
public class BizSynDataService extends CrudService2<BizSynDataDao, BizSynData> {

	public BizSynData get(Integer id) {
		return super.get(id);
	}
	
	public List<BizSynData> findList(BizSynData bizSynData) {
		return super.findList(bizSynData);
	}
	
	public Page<BizSynData> findPage(Page<BizSynData> page, BizSynData bizSynData) {
		return super.findPage(page, bizSynData);
	}
	
	@Transactional(readOnly = false)
	public void save(BizSynData bizSynData) {
		super.save(bizSynData);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizSynData bizSynData) {
		super.delete(bizSynData);
	}

	@Transactional(readOnly = false)
	public void insert(BizSynData bizSynData) {

		dao.insert(bizSynData);
	}
	
	public List<BizSynData> findPrePmSettleFinList(BizSynData bizSynData){
		return dao.findPrePmSettleFinList(bizSynData);
	}
	
}