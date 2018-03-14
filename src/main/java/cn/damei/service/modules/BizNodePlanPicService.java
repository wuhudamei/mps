
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizNodePlanPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizNodePlanPicDao;


@Service
@Transactional(readOnly = true)
public class BizNodePlanPicService extends CrudService2<BizNodePlanPicDao, BizNodePlanPic> {

	@Autowired
	private BizNodePlanPicDao bizNodePlanPicDao;
	
	public BizNodePlanPic get(Integer id) {
		return super.get(id);
	}
	
	public List<BizNodePlanPic> findList(BizNodePlanPic bizNodePlanPic) {
		return super.findList(bizNodePlanPic);
	}
	
	public Page<BizNodePlanPic> findPage(Page<BizNodePlanPic> page, BizNodePlanPic bizNodePlanPic) {
		return super.findPage(page, bizNodePlanPic);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNodePlanPic bizNodePlanPic) {
		super.save(bizNodePlanPic);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNodePlanPic bizNodePlanPic) {
		super.delete(bizNodePlanPic);
	}

	public List<BizNodePlanPic> getByNodePlanId(Integer nodeId) {
		return bizNodePlanPicDao.getByNodePlanId(nodeId);
	}
	
}