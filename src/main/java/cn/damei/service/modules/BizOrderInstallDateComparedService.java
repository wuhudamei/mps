package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallDateComparedDao;
import cn.damei.entity.modules.BizOrderInstallDateCompared;


@Service
@Transactional(readOnly = true)
public class BizOrderInstallDateComparedService
		extends CrudService2<BizOrderInstallDateComparedDao, BizOrderInstallDateCompared> {

	public BizOrderInstallDateCompared get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderInstallDateCompared> findList(BizOrderInstallDateCompared bizOrderInstallDateCompared) {
		return super.findList(bizOrderInstallDateCompared);
	}

}
