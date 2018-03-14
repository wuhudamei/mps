package cn.damei.dao.mobile.Worker;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.EmployeeGroupRa;

@MyBatisDao
public interface EmployeeGroupRaDao extends CrudDao2<EmployeeGroupRa>{

	EmployeeGroupRa findByEmployeeId(Integer employeeId);

}
