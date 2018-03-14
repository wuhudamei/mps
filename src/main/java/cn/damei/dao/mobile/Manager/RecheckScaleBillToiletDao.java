package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBillToilet;


@MyBatisDao
public interface RecheckScaleBillToiletDao extends CrudDao2<RecheckScaleBillToilet>{

	boolean insertToilet(RecheckScaleBillToilet taokou);

	List<RecheckScaleBillToilet> getByRecheckID(Integer recheckIDs);


}
