package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AppManagerOrder;


@MyBatisDao
public interface AppManagerOrderDao extends CrudDao2<AppManagerOrder>{


	List<AppManagerOrder> getByItemManagerId(Integer managerId);


	AppManagerOrder getById(Integer id);


	boolean updateByOrderStatusNumber(String orderstatus130Value, String orderstatus130ValueRemark, Integer id);

}
