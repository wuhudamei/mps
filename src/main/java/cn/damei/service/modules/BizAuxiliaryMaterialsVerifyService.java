package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.mobile.Manager.OrderTaskpackageAuxiliaryMaterialsDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackageAuxiliaryMaterials;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizAuxiliarMaterialsVerifySettlementRelDao;
import cn.damei.dao.modules.BizAuxiliaryMaterialsVerifyDao;
import cn.damei.dao.modules.BizAuxiliaryMaterialsVerifyIncludeDao;
import cn.damei.entity.modules.BizAuxiliarMaterialsVerifySettlementRel;
import cn.damei.entity.modules.BizAuxiliaryMaterialsVerify;
import cn.damei.entity.modules.BizAuxiliaryMaterialsVerifyInclude;
import cn.damei.dao.modules.BizOrderTaskpackageSettlementDao;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.dao.modules.BizSupplierDao;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 对账单service
 * 
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizAuxiliaryMaterialsVerifyService
		extends CrudService2<BizAuxiliaryMaterialsVerifyDao, BizAuxiliaryMaterialsVerify> {

	@Autowired
	private BizOrderTaskpackageSettlementDao bizOrderTaskpackageSettlementDao;

	@Autowired
	private OrderTaskpackageAuxiliaryMaterialsDao orderTaskpackageAuxiliaryMaterialsDao;

	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	
	@Autowired
	private BizAuxiliaryMaterialsVerifyIncludeDao bizAuxiliaryMaterialsVerifyIncludeDao;
	
	@Autowired
	private BizAuxiliarMaterialsVerifySettlementRelDao bizAuxiliarMaterialsVerifySettlementRelDao;

	@Autowired
	private BizSupplierDao bizSupplierDao;
	
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	public BizAuxiliaryMaterialsVerify get(Integer id) {
		return super.get(id);
	}

	public List<BizAuxiliaryMaterialsVerify> findList(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify) {
		return super.findList(bizAuxiliaryMaterialsVerify);
	}

	public Page<BizAuxiliaryMaterialsVerify> findPage(Page<BizAuxiliaryMaterialsVerify> page,
			BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify) {
		return super.findPage(page, bizAuxiliaryMaterialsVerify);
	}

	@Transactional(readOnly = false)
	public void save(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify) {
		super.save(bizAuxiliaryMaterialsVerify);
	}

	@Transactional(readOnly = false)
	public void delete(BizAuxiliaryMaterialsVerify bizAuxiliaryMaterialsVerify) {
		super.delete(bizAuxiliaryMaterialsVerify);
	}

	/**
	 * 生成对账单
	 */
	@Transactional(readOnly = false)
	public String createVerify(Integer storeId, String startDate, String endDate){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			User user = UserUtils.getUser();
			//根据当前用户的手机号查出供应商
			List<BizSupplier> supplierList = bizSupplierDao.findListByPhone(user.getPhone());
			if(supplierList==null || supplierList .size()==0){
				return "noSupplier";
			}
			String supplierId=supplierList.get(0).getId();
			BizAuxiliaryMaterialsVerify verify = new BizAuxiliaryMaterialsVerify();
			verify.setVerifyCode(bizSeiralnumService.getDateSequence("DZPC"));
			verify.setGenerateDatetime(new Date());
			verify.setStatus("10");
			verify.setStatusDatetime(new Date());
			verify.setSupplierId(supplierId);
			verify.setStatusRemark("供应商对照单生成");
			 if(StringUtils.isNoneBlank(user.getEmpId())){
				verify.setStatusOperateEmployeeId(Integer.parseInt(user.getEmpId()));
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("storeId", storeId);
			param.put("startDate", sdf.parse(startDate));
			param.put("endDate", sdf.parse(endDate));
			param.put("supplierId", supplierId);
			// 获取有效的结算单
			List<BizOrderTaskpackageSettlement> orderTaskpackSettleList = bizOrderTaskpackageSettlementDao
					.getPassOrderTaskPageSettle(param);
			
			// 获取有效结算单下的所有辅料信息
			List<OrderTaskpackageAuxiliaryMaterials> auxiliaryList = orderTaskpackageAuxiliaryMaterialsDao
					.queryAuxiliaryMaterialByPassOrderTaskpackageSettle(param);
			if(auxiliaryList == null){
				auxiliaryList = new ArrayList<OrderTaskpackageAuxiliaryMaterials>();
			}
			if(orderTaskpackSettleList == null || orderTaskpackSettleList.size()==0 ||auxiliaryList == null ||auxiliaryList.size()==0){
				return "noVerify";
			}
			if (orderTaskpackSettleList.size() > 0) {
				verify.setStoreId(storeId);
				verify.setStartDate(sdf.parse(startDate));
				verify.setEndDate(sdf.parse(endDate));
				verify.setOrderTaskpackageSettlementCount(orderTaskpackSettleList.size());
				verify.setAuxiliaryMaterialsCount(auxiliaryList.size());
				super.save(verify);
				
				// 添加状态日志信息
				BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
				statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_701);
				statusLog.setBusinessOnlyMarkInt(verify.getId());
				statusLog.setBusinessStatus(verify.getStatus());
				statusLog.setStatusDatetime(new Date());
				if (StringUtils.isNoneBlank(user.getEmpId())) {
					statusLog.setBusinessEmployeeId(Integer.parseInt(user.getEmpId()));
				}
				statusLog.setBusinessRemarks(verify.getStatusRemark());
				statusLog.preInsert();
				bizBusinessStatusLogDao.insert(statusLog);
			}
	        if(verify.getId()!=null){
	        	for(BizOrderTaskpackageSettlement settle:orderTaskpackSettleList){
	        		BizAuxiliarMaterialsVerifySettlementRel settleRel = new BizAuxiliarMaterialsVerifySettlementRel();
	        		settleRel.setAuxiliaryMaterialsVerifyId(verify.getId());
	        		settleRel.setOrderTaskpackageSettlementId(settle.getId());
	        		settleRel.preInsert();
	        		bizAuxiliarMaterialsVerifySettlementRelDao.insert(settleRel);
	        	}
	        	double laborTotalMoney=0;
	        	double supperTotalMoney = 0;
	        	double wangzhenTotalMoney= 0;
	        	for (OrderTaskpackageAuxiliaryMaterials auxiliary : auxiliaryList) {
	        		double laborPriceAmount = auxiliary.getUsedCount() * auxiliary.getPrice();
	    			double supperPriceAmount = auxiliary.getUsedCount() * auxiliary.getSupplierPrice();
	    			double wangzhenPriceAmount = auxiliary.getUsedCount() * auxiliary.getWangzhenPrice();
	        		laborTotalMoney = laborTotalMoney+laborPriceAmount;
	    			supperTotalMoney = supperTotalMoney + supperPriceAmount;
	    			wangzhenTotalMoney = wangzhenTotalMoney + wangzhenPriceAmount;
	    			BizAuxiliaryMaterialsVerifyInclude include = new BizAuxiliaryMaterialsVerifyInclude();
	    			include.setAuxiliaryMaterialsVerifyId(verify.getId());
	    			include.setAuxiliaryMaterialsNo(auxiliary.getAuxiliaryMaterialsNo());
	    			include.setAuxiliaryMaterialsCount(auxiliary.getUsedCount().intValue());
	    			include.setAuxiliaryMaterialsLaborPrice(auxiliary.getPrice());
	    			include.setAuxiliaryMaterialsSupplierPrice(auxiliary.getSupplierPrice());
	    			include.setAuxiliaryMaterialsWangzhenPrice(auxiliary.getWangzhenPrice());
	    			include.setAuxiliaryMaterialsLaborPriceAmount(laborPriceAmount);
	    			include.setAuxiliaryMaterialsSupplierPriceAmount(supperPriceAmount);
	    			include.setAuxiliaryMaterialsWangzhenPriceAmount(wangzhenPriceAmount);
	    			include.preInsert();
	    			bizAuxiliaryMaterialsVerifyIncludeDao.insert(include);
	    		}
	        	verify.setAuxiliaryMaterialsLaborPriceAmount(laborTotalMoney);
	        	verify.setAuxiliaryMaterialsSupplierPriceAmount(supperTotalMoney);
	        	verify.setAuxiliaryMaterialsWangzhenPriceAmount(wangzhenTotalMoney);
	        	super.save(verify);
	        }
	        return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	/**
	 * 修改对账单的状态,并添加状态日志
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateVerifyStatus(BizAuxiliaryMaterialsVerify verify){
		String result = "ok";
		try {
			String statusRemark=null;
			if(verify.getStatus().equals("10")){
	        	statusRemark="供应商对账单生成";
			}else if(verify.getStatus().equals("20")){
	        	statusRemark="供应商对账单申请";
			}else if(verify.getStatus().equals("15")){
				statusRemark="供应商对账单作废";
			}else if(verify.getStatus().equals("25")){
				statusRemark="工程部对账单审核驳回";
			}else if(verify.getStatus().equals("30")){
				statusRemark="工程部对账单审核通过";
			}else if(verify.getStatus().equals("35")){
				statusRemark="网真对账单审核驳回";
			}else if(verify.getStatus().equals("40")){
				statusRemark="网真对账单审核通过";
			}
	        verify.setStatusRemark(statusRemark);
			super.save(verify);
			// 添加状态日志信息
			User user = UserUtils.getUser();
			BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
			statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_701);
			statusLog.setBusinessOnlyMarkInt(verify.getId());
			statusLog.setBusinessStatus(verify.getStatus());
			statusLog.setStatusDatetime(new Date());
			if (StringUtils.isNoneBlank(user.getEmpId())) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(user.getEmpId()));
			}
			statusLog.setBusinessRemarks(verify.getStatusRemark());
			statusLog.preInsert();
			bizBusinessStatusLogDao.insert(statusLog);
		} catch (Exception e) {
			e.printStackTrace();
			result="error";
		}
		return result;
	}
}
