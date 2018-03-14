/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardReceiveDetailDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;

/**
 * 标化辅材领取详情Service
 * @author 汪文文
 * @version 2016-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardReceiveDetailService extends CrudService2<BizMaterialsStandardReceiveDetailDao, BizMaterialsStandardReceiveDetail> {
	
	
	public BizMaterialsStandardReceiveDetail get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsStandardReceiveDetail> findList(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail) {
		return super.findList(bizMaterialsStandardReceiveDetail);
	}
	
	public Page<BizMaterialsStandardReceiveDetail> findPage(Page<BizMaterialsStandardReceiveDetail> page, BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail) {
		return super.findPage(page, bizMaterialsStandardReceiveDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail) {
		super.save(bizMaterialsStandardReceiveDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail) {
		super.delete(bizMaterialsStandardReceiveDetail);
	}
	
	@Transactional(readOnly = false)
	public void add(BizMaterialsStandardReceiveDetail detail) {
		//根据billId查询
		boolean flag = true;
		List<BizMaterialsStandardReceiveDetail>  list = dao.findDetailsByBillId(detail.getMaterialsStandardReceiveBillId());
		if(list != null && list.size()>0){
			for (BizMaterialsStandardReceiveDetail exited : list) {
				if(exited.getMaterialsId().equals(detail.getMaterialsId())){
					//exited.setReceiveNumber(detail.getReceiveNumber());
					//exited.setMaterialsAmount(detail.getMaterialsAmount());
					flag = false;
					detail.setId(exited.getId());
					//dao.update(detail);
					break;
				}/*else{
					detail.preInsert();
					dao.insert(detail);
					break;
				}*/
			}
		}/*else {
			detail.preInsert();
			dao.insert(detail);
		}*/
		
		if(flag){
			detail.preInsert();
			dao.insert(detail);
		}else{
			detail.preUpdate();
			dao.update(detail);
		}
		
	}

	public List<BizMaterialsStandardReceiveDetail> findDetailsByBillId(Integer id) {
		
		return dao.findDetailsByBillId(id);
	}

	public List<BizMaterialsStandardReceiveDetail> findDetailsBySettleBillId(Integer billId) {
		
		return dao.findDetailsBySettleBillId(billId);
	}

	/**
	 * 
	 * @param list 　待添加修改的list
	 * @param billId
	 */
	@Transactional(readOnly = false)
	public void add1(List<BizMaterialsStandardReceiveDetail> list,Integer billId) {
		/*
		List<BizMaterialsStandardReceiveDetail> updateList = new ArrayList<BizMaterialsStandardReceiveDetail>();
		List<BizMaterialsStandardReceiveDetail> insertList = new ArrayList<BizMaterialsStandardReceiveDetail>();
		List<BizMaterialsStandardReceiveDetail>  details = dao.findDetailsByBillId(billId);//已存在的标化辅材详情
		if(details.size()>0){
			for (BizMaterialsStandardReceiveDetail detail : list) {
				boolean flag = true;
				for(BizMaterialsStandardReceiveDetail detail1 : details){
					if(detail.getMaterialsStandardReceiveBillId() == detail1.getMaterialsStandardReceiveBillId() && detail.getMaterialsId()  == detail1.getMaterialsId()){
						flag = false;
						detail.setId(detail1.getId());
						break;
					}
				}
				if(flag){
					detail.preInsert();
					insertList.add(detail);
				}else{
					detail.preUpdate();
					updateList.add(detail);
				}
			}
		}else{
			insertList.addAll(list);
			for (BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail : insertList) {
				bizMaterialsStandardReceiveDetail.preInsert();
			}
		}
		try{
			if(insertList.size()>0){
				dao.insert1(insertList);
			}
			if(updateList.size()>0){
				dao.update1(updateList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	*/
		List<BizMaterialsStandardReceiveDetail>  details = dao.findDetailsByBillId(billId);//已存在的标化辅材详情
		if(null != details && details.size()>0){
			for (BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail : list) {
				dao.updateDetails(bizMaterialsStandardReceiveDetail.getMaterialsId(),billId,bizMaterialsStandardReceiveDetail.getMaterialsAmount(),bizMaterialsStandardReceiveDetail.getReceiveNumber());
			}
		}else{
			dao.insert1(list);
		}
		}
	//根据 billId materilsId 修改detail 的快照详情
		@Transactional
		public void updateDtaileSnape(BizMaterialsStandardReceiveDetail detail){
			dao.updateDtaileSnape(detail);
		}
		@Transactional
		public void updateDtaileApplySnape(String billId){
			dao.updateDtaileApplySnape(billId);
		}
		public String getOrderId(String billId){
			return dao.getOrderId(billId);
		}
	}