package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.PmSubsidyCnfg;
@MyBatisDao
public interface PmSubsidyCnfgDao extends CrudDao2<PmSubsidyCnfg> {

	List<PmSubsidyCnfg> findAllPmSubsidyCnfg(PmSubsidyCnfg pmSubsidyCnfg);

	void isEnabel(PmSubsidyCnfg pmSubsidyCnfg);

}
