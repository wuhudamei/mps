package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPurchasePictureDao;
import cn.damei.entity.modules.BizPurchasePicture;

@Service
@Transactional(readOnly = true)
public class BizPurchasePictureService extends CrudService2<BizPurchasePictureDao,BizPurchasePicture >{

	public List<BizPurchasePicture> findPictureByPurchaseId(Integer purchaseId) {
		
		return dao.findPictureByPurchaseId(purchaseId);
	}

	
	
}
