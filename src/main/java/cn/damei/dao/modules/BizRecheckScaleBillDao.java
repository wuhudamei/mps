package cn.damei.dao.modules;

import java.util.Date;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBill;


@MyBatisDao
public interface BizRecheckScaleBillDao extends CrudDao2<BizRecheckScaleBill>{

	void updateByRecheckStatus(String status, String remarks, Date addDate, Integer recheckID);

}
