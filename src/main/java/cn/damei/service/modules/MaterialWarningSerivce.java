package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.MaterialWarningDao;
import cn.damei.entity.modules.MaterialWarning;
@Service
public class MaterialWarningSerivce extends CrudService<MaterialWarningDao,MaterialWarning>{

	@Autowired
	private MaterialWarningDao materialWarningDao;
	public List<String> findAllDelayOrderId(int i) {

		return materialWarningDao.findAllDelayOrderId(i);
	}
	public List<String> findCompleteOrderId() {

		return materialWarningDao.findCompleteOrderId();
	}
	public List<MaterialWarning> findCountOrder(List<String> list) {

		return materialWarningDao.findCountOrder(list);
	}

}
