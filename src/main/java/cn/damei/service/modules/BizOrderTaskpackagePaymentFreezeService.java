package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentFreezeDao;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentFreeze;

/**
 * 付款单冻结/解冻Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentFreezeService extends CrudService2<BizOrderTaskpackagePaymentFreezeDao,BizOrderTaskpackagePaymentFreeze>{

	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;
	
	public List<BizOrderTaskpackagePaymentFreeze> findList(BizOrderTaskpackagePaymentFreeze bizOrderTaskpackagePaymentFreeze){
		return dao.findList(bizOrderTaskpackagePaymentFreeze);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePaymentFreeze bizOrderTaskpackagePaymentFreeze) {
		super.save(bizOrderTaskpackagePaymentFreeze);
	}
	
	/**
	 * 冻结/解冻付款单
	 * @param id
	 * @param status
	 * @return
	 */
	@Transactional(readOnly = false)
	public String freezePayment(Integer id,String status,String frozenRemarks,int isFrozenType){
		String result = "success";
		String frozenType=null;
		//修改付款单状态
		BizOrderTaskpackagePayment bizOrderTaskpackagePayment = bizOrderTaskpackagePaymentDao.get(id);
		if(status.equals("18")){//冻结
			if(bizOrderTaskpackagePayment.getStatus().equals("18")){//已冻结
				return "repeat";
			}else{
				frozenType="1";
			}
		}else if(status.equals("15")){//解冻
            if(!bizOrderTaskpackagePayment.getStatus().equals("18")){//已解冻
            	return "repeat";
			}else{
				frozenType="2";
			}
		}
		bizOrderTaskpackagePayment.setStatus(status);
		bizOrderTaskpackagePayment.setStatusDatetime(new Date());
		bizOrderTaskpackagePayment.preUpdate();
		bizOrderTaskpackagePaymentDao.update(bizOrderTaskpackagePayment);
		//添加冻结解冻信息
		BizOrderTaskpackagePaymentFreeze  bizOrderTaskpackagePaymentFreeze = new BizOrderTaskpackagePaymentFreeze();
		bizOrderTaskpackagePaymentFreeze.setBizOrderTaskpackagePaymentId(id);
		bizOrderTaskpackagePaymentFreeze.setFrozenRemarks(frozenRemarks);
		bizOrderTaskpackagePaymentFreeze.setFrozenType(frozenType);
		bizOrderTaskpackagePaymentFreeze.preInsert();
		dao.insert(bizOrderTaskpackagePaymentFreeze);

		if(isFrozenType == 1 && status.equals("18")){
			BizOrderTaskpackagePayment balanceParment = bizOrderTaskpackagePaymentDao.findBalancePaymentByPaymentId(id);
			if(balanceParment != null){
				balanceParment.setStatus(status);
				balanceParment.setStatusDatetime(new Date());
				balanceParment.preUpdate();
				bizOrderTaskpackagePaymentDao.update(balanceParment);
				
				BizOrderTaskpackagePaymentFreeze balancePaymentFreeze = new BizOrderTaskpackagePaymentFreeze();
				balancePaymentFreeze.setBizOrderTaskpackagePaymentId(balanceParment.getId());
				balancePaymentFreeze.setFrozenRemarks(frozenRemarks);
				balancePaymentFreeze.setFrozenType(frozenType);
				balancePaymentFreeze.preInsert();
				dao.insert(balancePaymentFreeze);
			}
		}
		
		return result;
	}
}