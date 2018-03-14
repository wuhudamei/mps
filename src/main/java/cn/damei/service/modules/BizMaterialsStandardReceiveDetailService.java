
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardReceiveDetailDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;


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

		boolean flag = true;
		List<BizMaterialsStandardReceiveDetail>  list = dao.findDetailsByBillId(detail.getMaterialsStandardReceiveBillId());
		if(list != null && list.size()>0){
			for (BizMaterialsStandardReceiveDetail exited : list) {
				if(exited.getMaterialsId().equals(detail.getMaterialsId())){


					flag = false;
					detail.setId(exited.getId());

					break;
				}
			}
		}
		
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


	@Transactional(readOnly = false)
	public void add1(List<BizMaterialsStandardReceiveDetail> list,Integer billId) {

		List<BizMaterialsStandardReceiveDetail>  details = dao.findDetailsByBillId(billId);
		if(null != details && details.size()>0){
			for (BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail : list) {
				dao.updateDetails(bizMaterialsStandardReceiveDetail.getMaterialsId(),billId,bizMaterialsStandardReceiveDetail.getMaterialsAmount(),bizMaterialsStandardReceiveDetail.getReceiveNumber());
			}
		}else{
			dao.insert1(list);
		}
		}

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