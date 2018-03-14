package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillCurtain;


@MyBatisDao
public interface RecheckScaleBillCurtainDao extends CrudDao2<RecheckScaleBillCurtain>{

	boolean insertCurtain(RecheckScaleBillCurtain curtain);

	List<RecheckScaleBillCurtain> getByRecheckID(Integer recheckIDs);


}
