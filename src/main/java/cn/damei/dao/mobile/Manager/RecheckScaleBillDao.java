package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBill;


@MyBatisDao
public interface RecheckScaleBillDao extends CrudDao2<RecheckScaleBill>{

	List<RecheckScaleBill> queryListByOrderID(Integer orderID);

	RecheckScaleBill getByID(Integer recheckIDs);


}
