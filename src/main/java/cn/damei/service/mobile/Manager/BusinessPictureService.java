package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BusinessPictureDao;
import cn.damei.entity.mobile.Manager.BusinessPicture;
@Service
@Transactional(readOnly = true)
public class BusinessPictureService extends CrudService2<BusinessPictureDao, BusinessPicture>{

	public List<BusinessPicture> queryPicture(Integer purchaseId, String pictureBusinessType5) {
		
		return dao.queryPicture(purchaseId,pictureBusinessType5);
	}

	@Transactional(readOnly = false)
	public void savePic(BusinessPicture picture) {
		
		dao.savePic(picture);
	}

	@Transactional(readOnly = false)
	public void deletePic(Integer id) {
		dao.deletePic(id);
		
	}

	public List<BusinessPicture> queryByReceiveBillId(Integer id) {

		return dao.queryByReceiveBillId(id);
	}

	public List<BusinessPicture> queryOverPicture(Integer id, String pictureBusinessType104) {

		return dao.queryOverPicture(id,pictureBusinessType104);
	}


	public List<BusinessPicture> queryPictureByBussinessIdAndType(Integer id, String type) {

		return dao.queryPictureByBussinessIdAndType(id,type);
	}

}
