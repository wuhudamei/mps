package cn.damei.entity.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderConfirmStartworkPicDao;
import cn.damei.entity.modules.BizOrderConfirmStartworkPic;


@Service
@Transactional(readOnly = true)
public class BizOrderConfirmStartworkPicService extends CrudService2<BizOrderConfirmStartworkPicDao, BizOrderConfirmStartworkPic>{

	@Autowired
	private BizOrderConfirmStartworkPicDao bizOrderConfirmStartworkPicDao;

	public List<BizOrderConfirmStartworkPic> getByConfirmStartWorkID(Integer startWorkID) {

		return bizOrderConfirmStartworkPicDao.getByConfirmStartWorkID(startWorkID);
	}

}
