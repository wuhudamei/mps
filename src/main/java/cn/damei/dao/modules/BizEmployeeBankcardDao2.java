package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeBankcard2;

@MyBatisDao
public interface BizEmployeeBankcardDao2 extends CrudDao2<BizEmployeeBankcard2>{

	/**
	 * queryEmployeeBankcardByEmpId
	 * @param empId
	 * @return
	 */
	public List<BizEmployeeBankcard2> queryEmployeeBankcardByEmpId(Integer empId);
}
