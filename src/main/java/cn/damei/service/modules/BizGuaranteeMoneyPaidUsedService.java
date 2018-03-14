package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.GuaranteeMoneyConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizGuaranteeMoneyBalanceDao;
import cn.damei.dao.modules.BizGuaranteeMoneyPaidUsedDao;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.entity.modules.BizGuaranteeMoneyPaidUsed;

@Service
@Transactional(readOnly = true)
public class BizGuaranteeMoneyPaidUsedService extends CrudService2<BizGuaranteeMoneyPaidUsedDao,BizGuaranteeMoneyPaidUsed>{

	@Autowired
	private BizGuaranteeMoneyBalanceDao bizGuaranteeMoneyBalanceDao;
	
	public  BizGuaranteeMoneyPaidUsed get(Integer id){
		return super.get(id);
	}
	
	public List<BizGuaranteeMoneyPaidUsed> findList(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed) {
		return super.findList(bizGuaranteeMoneyPaidUsed);
	}
	
	public Page<BizGuaranteeMoneyPaidUsed> findPage(Page<BizGuaranteeMoneyPaidUsed> page, BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed) {
		return super.findPage(page, bizGuaranteeMoneyPaidUsed);
	}
	
	public Page<BizGuaranteeMoneyPaidUsed> findManagerGuaranteeMoneyAmountPaidSettle(Page<BizGuaranteeMoneyPaidUsed> page, BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed){
		bizGuaranteeMoneyPaidUsed.setPage(page);
		page.setList(dao.findManagerGuaranteeMoneyAmountPaidSettle(bizGuaranteeMoneyPaidUsed));
		return page;
	}
	
	public Page<BizGuaranteeMoneyPaidUsed> findWorkerGuaranteeMoneyAmountPaidSettle(Page<BizGuaranteeMoneyPaidUsed> page, BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed){
		bizGuaranteeMoneyPaidUsed.setPage(page);
		page.setList(dao.findWorkerGuaranteeMoneyAmountPaidSettle(bizGuaranteeMoneyPaidUsed));
		return page;
	}
	
	
	@Transactional(readOnly = false)
	public void save(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed){
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceDao.findGuaranteeMoneyBalanceByEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
		if(bizGuaranteeMoneyPaidUsed.getId()!=null){
			BizGuaranteeMoneyPaidUsed oldBizGuaranteeMoneyPaidUsed = super.get(bizGuaranteeMoneyPaidUsed);
			if(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyType().equals(GuaranteeMoneyConstantUtil.guaranteeMoneyType_2)){
				bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyTypeAmountTotal(bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidUsed()-oldBizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount()+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
				bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()+oldBizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount()-bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
				
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyBalance());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidUsed(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyTypeAmountTotal());
			}else if(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyType().equals(GuaranteeMoneyConstantUtil.guaranteeMoneyType_1)){
				bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyTypeAmountTotal(bizGuaranteeMoneyBalance.getGuaranteMoneyAmountPaidOffline()-oldBizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount()+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
				bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()-oldBizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount()+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
			    
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyBalance());
				bizGuaranteeMoneyBalance.setGuaranteMoneyAmountPaidOffline(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyTypeAmountTotal());
			}
			
			super.save(bizGuaranteeMoneyPaidUsed);
			
			bizGuaranteeMoneyBalance.preUpdate();
			
			bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
		}else{
			if(bizGuaranteeMoneyBalance != null && bizGuaranteeMoneyBalance.getId() != null){
				if(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyType().equals(GuaranteeMoneyConstantUtil.guaranteeMoneyType_2)){
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyTypeAmountTotal(bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidUsed()+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()-bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
					
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyBalance());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidUsed(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyTypeAmountTotal());
				}else if(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyType().equals(GuaranteeMoneyConstantUtil.guaranteeMoneyType_1)){
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyTypeAmountTotal(bizGuaranteeMoneyBalance.getGuaranteMoneyAmountPaidOffline()+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
				    
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyBalance());
					bizGuaranteeMoneyBalance.setGuaranteMoneyAmountPaidOffline(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyTypeAmountTotal());
				}
				super.save(bizGuaranteeMoneyPaidUsed);
				
				bizGuaranteeMoneyBalance.preUpdate();
				
				bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
			}else{
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
				if(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyType().equals(GuaranteeMoneyConstantUtil.guaranteeMoneyType_2)){
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyTypeAmountTotal(0+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyBalance(0-bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
					
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyBalance());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidUsed(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyTypeAmountTotal());
				}else if(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyType().equals(GuaranteeMoneyConstantUtil.guaranteeMoneyType_1)){
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyTypeAmountTotal(0+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyPaidUsed.setGuaranteeMoneyBalance(0+bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyAmount());
				    
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyBalance());
					bizGuaranteeMoneyBalance.setGuaranteMoneyAmountPaidOffline(bizGuaranteeMoneyPaidUsed.getGuaranteeMoneyTypeAmountTotal());
				}
				super.save(bizGuaranteeMoneyPaidUsed);
				bizGuaranteeMoneyBalance.setEmployeeId(bizGuaranteeMoneyPaidUsed.getUsedEmployeeId());
				bizGuaranteeMoneyBalance.preInsert();
				bizGuaranteeMoneyBalanceDao.insert(bizGuaranteeMoneyBalance);
			}
		}
		
		
	}
	
	@Transactional(readOnly = false)
	public void delete(BizGuaranteeMoneyPaidUsed bizGuaranteeMoneyPaidUsed) {
		super.delete(bizGuaranteeMoneyPaidUsed);
	}
}
