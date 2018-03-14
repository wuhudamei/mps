package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizEmployeeBankcardDao2;
import cn.damei.entity.modules.BizEmployeeBankcard2;

@Service
@Transactional(readOnly = true)
public class BizEmployeeBankcardService2 extends CrudService2<BizEmployeeBankcardDao2, BizEmployeeBankcard2>{
	
	@Transactional(readOnly = false)
	public void insert(BizEmployeeBankcard2 bizEmployeeBankcard) {
		dao.insert(bizEmployeeBankcard);
	}
	
	@Transactional(readOnly = false)
	public void update(BizEmployeeBankcard2 bizEmployeeBankcard) {
		// TODO Auto-generated method stub
		dao.update(bizEmployeeBankcard);
	}

}
