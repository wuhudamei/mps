package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallPlanPicDao;
import cn.damei.entity.modules.BizOrderInstallPlanPic;


@Service
@Transactional(readOnly = true)
public class BizOrderInstallPlanPicService extends CrudService2<BizOrderInstallPlanPicDao, BizOrderInstallPlanPic> {

	@Autowired
	private BizOrderInstallPlanPicDao bizOrderInstallPlanPicDao;

	public List<BizOrderInstallPlanPic> getByIdList(Integer orderInstallPlanId) {
		return bizOrderInstallPlanPicDao.getByIdList(orderInstallPlanId);
	}

}
