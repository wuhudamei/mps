package cn.damei.service.mobile.Worker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.EmployeeGroupVoDao;
import cn.damei.entity.mobile.Worker.EmployeeGroupVo;

@Service
@Transactional(readOnly = true)
public class EmployeeGroupVoService extends CrudService2<EmployeeGroupVoDao, EmployeeGroupVo>{

	public EmployeeGroupVo findById(Integer id) {
		return dao.get(id);
	}
	
}
