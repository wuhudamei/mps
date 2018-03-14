package cn.damei.entity.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderConfirmStartworkPicDao;
import cn.damei.entity.modules.BizOrderConfirmStartworkPic;

/**
 * 确认开工功能
 * @author llp
 *
 */
@Service
@Transactional(readOnly = true)
public class BizOrderConfirmStartworkPicService extends CrudService2<BizOrderConfirmStartworkPicDao, BizOrderConfirmStartworkPic>{

	@Autowired
	private BizOrderConfirmStartworkPicDao bizOrderConfirmStartworkPicDao;//确认开工功能

	public List<BizOrderConfirmStartworkPic> getByConfirmStartWorkID(Integer startWorkID) {
		// TODO Auto-generated method stub
		return bizOrderConfirmStartworkPicDao.getByConfirmStartWorkID(startWorkID);
	}

}
