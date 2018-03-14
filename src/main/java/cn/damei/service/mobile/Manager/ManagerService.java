package cn.damei.service.mobile.Manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ManagerDao;
import cn.damei.entity.mobile.Manager.Manager;

@Service
@Transactional(readOnly = true)
public class ManagerService extends CrudService2<ManagerDao, Manager>{

	public Manager selectManagerByPhone(String phone) {
		
		return dao.selectManagerByPhone(phone);
	}

}
