package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.PmSubsidyCnfgDao;
import cn.damei.entity.modules.PmSubsidyCnfg;
@Service
public class PmSubsidyCnfgService extends CrudService2<PmSubsidyCnfgDao,PmSubsidyCnfg>{

	public List<PmSubsidyCnfg> findAllPmSubsidyCnfg(PmSubsidyCnfg pmSubsidyCnfg) {

		return dao.findAllPmSubsidyCnfg(pmSubsidyCnfg);
	}
	@Transactional(readOnly=false)
	public void isEnabel(PmSubsidyCnfg pmSubsidyCnfg) {

		dao.isEnabel(pmSubsidyCnfg);
	}

}
