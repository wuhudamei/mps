
package cn.damei.service.modules;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.OrderTaskpackDao;
import cn.damei.entity.modules.OrderTaskpack;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.dao.modules.OrderTaskpackageProcedureDao;
import cn.damei.entity.modules.OrderTaskpackageProcedure;
import cn.damei.dao.modules.BizProcedureDao;
import cn.damei.entity.modules.BizProcedure;
import cn.damei.dao.modules.BizProcedurePriceDao;
import cn.damei.entity.modules.BizProcedurePrice;
import cn.damei.dao.modules.OrderTaskpackageDao;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class OrderTaskpackageProcedureService extends CrudService<OrderTaskpackageProcedureDao, OrderTaskpackageProcedure> {

	@Autowired
	private OrderTaskpackageProcedureDao orderTaskpackageProcedureDao;
	
	@Autowired
	private OrderTaskpackageDao orderTaskpackageDao;
	
	@Autowired
	private BizProcedureDao bizProcedureDao;
	
	@Autowired
	private BizProcedurePriceDao bizProcedurePriceDao;
	
	@Autowired
	private OrderTaskpackDao orderTaskpackDao;
	
	public OrderTaskpackageProcedure get(String id) {
		return super.get(id);
	}
	
	public List<OrderTaskpackageProcedure> findList(OrderTaskpackageProcedure orderTaskpackageProcedure) {
		return super.findList(orderTaskpackageProcedure);
	}
	
	public Page<OrderTaskpackageProcedure> findPage(Page<OrderTaskpackageProcedure> page, OrderTaskpackageProcedure orderTaskpackageProcedure) {
		return super.findPage(page, orderTaskpackageProcedure);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderTaskpackageProcedure orderTaskpackageProcedure) {
		super.save(orderTaskpackageProcedure);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderTaskpackageProcedure orderTaskpackageProcedure) {
		super.delete(orderTaskpackageProcedure);
	}

	@SuppressWarnings("unused")
	@Transactional(readOnly = false)
	public boolean insertByOrder1(OrderTaskpackageProcedure tp) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("tp.getTaskpackageId()"+tp.getTaskpackageId());
		
		OrderTaskpack taskpack = orderTaskpackDao.getByOrderId(Integer.valueOf(tp.getOrderId()));
		
		BizProcedure bizProcedure = bizProcedureDao.getByProcedureNo(tp.getProcedureNo());
		OrderTaskpackage orderTaskpackage =orderTaskpackageDao.getByOrderIdB(tp.getOrderId(),tp.getTaskpackageNumber());
		BizProcedurePrice bizProcedurePrice = bizProcedurePriceDao.getByProcedureNo(tp.getProcedureNo(),
				orderTaskpackage.getStoreId(),sf.format(taskpack.getContractStartDate()).toString(),tp.getProcedureNo(),orderTaskpackage.getStoreId(),orderTaskpackage.getProjectMode());
		
		OrderTaskpackageProcedure opt = new OrderTaskpackageProcedure();
		opt.setId(null);
    	opt.setPackageName(orderTaskpackage.getPackageName());
		opt.setProcedureName(bizProcedure.getProcedureName());
		opt.setMeasurementUnit(bizProcedure.getMeasurementUnit());
		if(null != bizProcedurePrice){
			opt.setLaborPrice(bizProcedurePrice.getLaborPrice().doubleValue());
			opt.setAccessoriesPrice(bizProcedurePrice.getAccessoriesPrice().doubleValue());
			opt.setSynthesizePrice(bizProcedurePrice.getSynthesizePrice().doubleValue());
		}else{
			opt.setLaborPrice(0.00);
			opt.setAccessoriesPrice(0.00);
			opt.setSynthesizePrice(0.00);
		}
		
		if(null != tp){
			opt.setBudgetNumber(tp.getBudgetNumber());
			opt.setLaborAuxiliaryMaterialsBudgetAmount(opt.getBudgetNumber() * opt.getSynthesizePrice());
			opt.setTaskpackageId(Long.parseLong(tp.getTaskpackageId().toString()));
			opt.setProcedureNo(tp.getProcedureNo());
			opt.setRemarks(tp.getRemarks());
			opt.setLaborBudgetAmount(opt.getBudgetNumber() * opt.getLaborPrice());
			opt.setAuxiliaryMaterialsBudgetAmount(opt.getBudgetNumber() * opt.getAccessoriesPrice());
		}else{
			opt.setBudgetNumber(Double.valueOf(0.00));
			opt.setLaborAuxiliaryMaterialsBudgetAmount(Double.valueOf(0.00));
			opt.setTaskpackageId(null);
			opt.setProcedureNo(null);
			opt.setLaborBudgetAmount(Double.valueOf(0.00));
			opt.setAuxiliaryMaterialsBudgetAmount(Double.valueOf(0.00));
		}

		opt.setCreateBy(UserUtils.getUser().getCreateBy());
		opt.setCreateDate(new Date());
		opt.setUpdateBy(UserUtils.getUser().getCreateBy());
		opt.setUpdateDate(new Date());
    	boolean bz = (orderTaskpackageProcedureDao.insertByOrder1(opt)) ? true : false;
    	return bz;
	}


	public List<OrderTaskpackageProcedure> getByTaskpackageId(Integer taskpackageId) {

		return orderTaskpackageProcedureDao.getByTaskpackageId(taskpackageId);
	}


	@Transactional(readOnly = false)
	public boolean insertProcedure(OrderTaskpackGenVo orderTaskpackGenVo,String taskPackageTemplatId) {
		OrderTaskpackageProcedure p = new OrderTaskpackageProcedure();
		
		p.setId(null);
		p.setTaskpackageId(Long.valueOf(taskPackageTemplatId));
		p.setPackageName(orderTaskpackGenVo.getTemplatName().trim());
		p.setProcedureNo(orderTaskpackGenVo.getProcedureNo().trim());
		p.setProcedureName(orderTaskpackGenVo.getProcedureName().trim());

		p.setMeasurementUnit(orderTaskpackGenVo.getMeasurementUnit());
		p.setLaborPrice(Double.valueOf(orderTaskpackGenVo.getLaborPrice().trim()));
		p.setAccessoriesPrice(Double.valueOf(orderTaskpackGenVo.getAccessoriesPrice().trim()));
		p.setSynthesizePrice(Double.valueOf(orderTaskpackGenVo.getSynthesizePrice().trim()));
		p.setBudgetNumber(Double.valueOf(0));
		p.setLaborAuxiliaryMaterialsBudgetAmount(Double.valueOf(0));
		p.setLaborBudgetAmount(Double.valueOf(0));
		p.setAuxiliaryMaterialsBudgetAmount(Double.valueOf(0));
		p.setRemarks(orderTaskpackGenVo.getRemarks());
		p.setCreateDate(DateUtils.addDate(new Date(), 0));
		p.setCreateBy(UserUtils.getUser().getCreateBy());
		p.setUpdateDate(DateUtils.addDate(new Date(), 0));
		p.setUpdateBy(UserUtils.getUser().getCreateBy());
		p.setDelFlag("0");
		p.setRealNumber(new BigDecimal("0.00"));

		return (orderTaskpackageProcedureDao.insertProcedure(p)) ? true :false;
	}


	@Transactional(readOnly = false)
	public boolean updateById(Double budgetNumber,Double total,String procedureID,String remarks,Double laborBudgetAmount, Double auxiliaryMaterialsBudgetAmount) {
		return (orderTaskpackageProcedureDao.updateById(budgetNumber,total,procedureID,remarks,laborBudgetAmount,auxiliaryMaterialsBudgetAmount)) ? true : false;
	}
	
}