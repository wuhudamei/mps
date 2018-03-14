package cn.damei.dao.mobile.Manager;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Manager;


@MyBatisDao
public interface ManagerDao extends CrudDao2<Manager>{

	Manager selectManagerByPhone(String phone);
	
}
