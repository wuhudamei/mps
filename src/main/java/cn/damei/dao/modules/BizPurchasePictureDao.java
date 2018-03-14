package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPurchasePicture;

@MyBatisDao
public interface BizPurchasePictureDao extends CrudDao2<BizPurchasePicture> {

	List<BizPurchasePicture> findPictureByPurchaseId(Integer purchaseId);
	
}
