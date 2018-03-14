package cn.damei.dao.mobile.Worker;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.GuarMoney;

import java.util.List;

@MyBatisDao
public interface GuarMoneyDao extends CrudDao2<GuarMoney>{

    public List<GuarMoney> queryGuarMoney(Integer employeeId);

    public Double queryGuarMoneyTotalAmount(Integer employeeId);
}
