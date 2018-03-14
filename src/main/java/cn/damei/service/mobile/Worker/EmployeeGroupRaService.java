package cn.damei.service.mobile.Worker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.EmployeeGroupRaDao;
import cn.damei.entity.mobile.Worker.EmployeeGroupRa;

@Service
@Transactional(readOnly = true)
public class EmployeeGroupRaService extends CrudService2<EmployeeGroupRaDao, EmployeeGroupRa>{

	public EmployeeGroupRa findByEmployeeId(Integer employeeId) {

		return dao.findByEmployeeId(employeeId);
	}

}
