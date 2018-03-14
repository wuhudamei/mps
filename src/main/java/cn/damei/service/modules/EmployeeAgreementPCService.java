package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.EmployeeAgreementPCDao;
import cn.damei.entity.modules.EmployeeAgreementPC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional(readOnly=false)
public class EmployeeAgreementPCService extends CrudService2<EmployeeAgreementPCDao, EmployeeAgreementPC>{

	
	
	public String employeeAgreementPCService(EmployeeAgreementPC employeeAgreementPC){
		employeeAgreementPC = dao.isSignEmployeeAgreement(employeeAgreementPC);
		//如果是空的话，没有签订协议 弹出窗口   0
		if(employeeAgreementPC == null){
			return "0";
		}
		//如果不是空的话
		//判断是否签订协议
		String isSignEmployeeAgreement = employeeAgreementPC.getIsSignEmployeeAgreement();
		//如果是 不弹窗口 1
		if(("1").equals(isSignEmployeeAgreement)){
			return "1";
		}else{
			//如果不是 弹出窗口 0
			//判断今天是否弹出过窗口
			Date employeeAgreementReadDatetime = employeeAgreementPC.getEmployeeAgreementReadDatetime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String read = sdf.format(employeeAgreementReadDatetime);
			String now = sdf.format(new Date());
			//如果相等不弹出窗口
			if(now.equals(read)){
				return "1";
			}else{
				return "0";
			}
		}
	}

	public void insertEmployeeAgreement(EmployeeAgreementPC employeeAgreement) {
		EmployeeAgreementPC employeeAgreementPC = dao.isSignEmployeeAgreement(employeeAgreement);
		if(employeeAgreementPC!=null){
			dao.update(employeeAgreement);
		}else{
			dao.insert(employeeAgreement);
		}
		
	}
}
