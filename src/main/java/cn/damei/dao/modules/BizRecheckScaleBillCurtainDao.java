package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillCurtain;


@MyBatisDao
public interface BizRecheckScaleBillCurtainDao extends CrudDao2<BizRecheckScaleBillCurtain>{

	List<BizRecheckScaleBillCurtain> getByRecheckID(Integer recheckIDs);

}
