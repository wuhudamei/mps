package cn.damei.service.mobile.Worker;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.GuarMoneyDao;
import cn.damei.entity.mobile.Worker.GuarMoney;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GuarMoneyService extends CrudService2<GuarMoneyDao, GuarMoney>{

	public List<GuarMoney> queryGuarMoney(Integer employeeId){
		return dao.queryGuarMoney(employeeId);
	}

	public Double queryGuarMoneyTotalAmount(Integer employeeId){
		return dao.queryGuarMoneyTotalAmount(employeeId);
	}
}
