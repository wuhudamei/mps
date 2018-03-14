package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillToilet;


@MyBatisDao
public interface BizRecheckScaleBillToiletDao extends CrudDao2<BizRecheckScaleBillToilet>{

	List<BizRecheckScaleBillToilet> getByRecheckID(Integer recheckIDs);

}
