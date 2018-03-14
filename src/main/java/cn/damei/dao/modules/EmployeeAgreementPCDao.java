package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.EmployeeAgreementPC;

@MyBatisDao 
public interface EmployeeAgreementPCDao extends CrudDao2<EmployeeAgreementPC>{

	EmployeeAgreementPC isSignEmployeeAgreement(EmployeeAgreementPC employeeAgreementPC);

}
