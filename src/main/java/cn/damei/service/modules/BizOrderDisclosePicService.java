package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderDisclosePicDao;
import cn.damei.entity.modules.BizOrderDisclosePic;

/**
 * 订单交底
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizOrderDisclosePicService extends CrudService2<BizOrderDisclosePicDao, BizOrderDisclosePic>{
	
	@Autowired
	private BizOrderDisclosePicDao bizOrderDisclosePicDao;
	
	public BizOrderDisclosePic get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderDisclosePic> findList(BizOrderDisclosePic bizOrderDisclosePic) {
		return super.findList(bizOrderDisclosePic);
	}
	
	public Page<BizOrderDisclosePic> findPage(Page<BizOrderDisclosePic> page, BizOrderDisclosePic bizOrderDisclosePic) {
		return super.findPage(page, bizOrderDisclosePic);
	}

	public List<BizOrderDisclosePic> getByOrderDiscloseId(
			Integer orderDiscloseId) {
		return bizOrderDisclosePicDao.getByOrderDiscloseId(orderDiscloseId);
	}	

}
