
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.RecheckCnfg;


@MyBatisDao
public interface RecheckCnfgDao extends CrudDao<RecheckCnfg> {


	RecheckCnfg findRecheckCnfgMessage();

	List<RecheckCnfg> findAllListz();

	void deleteall(RecheckCnfg recheckCnfg);

}