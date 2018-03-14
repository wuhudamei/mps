package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizGuaranteeMoneyBalanceDao;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;

@Service
@Transactional(readOnly = true)
public class BizGuaranteeMoneyBalanceService  extends CrudService2<BizGuaranteeMoneyBalanceDao,BizGuaranteeMoneyBalance>{

	public  BizGuaranteeMoneyBalance get(Integer id){
		return super.get(id);
	}
	
	public List<BizGuaranteeMoneyBalance> findList(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance) {
		return super.findList(bizGuaranteeMoneyBalance);
	}
	
	public Page<BizGuaranteeMoneyBalance> findPage(Page<BizGuaranteeMoneyBalance> page, BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance) {
		return super.findPage(page, bizGuaranteeMoneyBalance);
	}
	
	@Transactional(readOnly = false)
	public void save(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance){
		super.save(bizGuaranteeMoneyBalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance) {
		super.delete(bizGuaranteeMoneyBalance);
	} 
	
	public BizGuaranteeMoneyBalance findGuaranteeMoneyBalanceByEmployeeId(Integer employeeId){
		return dao.findGuaranteeMoneyBalanceByEmployeeId(employeeId);
	}
	
	public Page<BizGuaranteeMoneyBalance> findGuaranteeMoneyBalanceByParam(Page<BizGuaranteeMoneyBalance> page, BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance){
		bizGuaranteeMoneyBalance.setPage(page);
		if(bizGuaranteeMoneyBalance.getEmpType().equals("1")){//项目经理
			page.setList(dao.findManagerGuaranteeMoneyBalanceByParam(bizGuaranteeMoneyBalance));
		}else if(bizGuaranteeMoneyBalance.getEmpType().equals("2")){//工人
			page.setList(dao.findWorkerGuaranteeMoneyBalanceByParam(bizGuaranteeMoneyBalance));
		}
		
		return page;
	}
}
