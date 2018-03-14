package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.dao.modules.QualityControlFineUpdateDao;
import cn.damei.entity.modules.QualityControlFineUpdateEntity;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;

@Service
public class QualityControlFineUpdateService  extends CrudService<QualityControlFineUpdateDao, QualityControlFineUpdateEntity>{
	public String findSettleStatus(BizPmSettleCategoryDetail bps){
		return dao.findSettleStatus(bps);
	}
	@Transactional(readOnly = false)
	public void updateBizQcBillCheckItem(QualityControlFineUpdateEntity qc){
		dao.updateBizQcBillCheckItem(qc);
	}
	@Transactional(readOnly = false)
	public void insertBizQcBillCheckItemLog(Map<String,String> map){
		dao.insertBizQcBillCheckItemLog(map);
	}
	@Transactional(readOnly = false)
	public void updateBizPmSettleCategoryDetail(BizPmSettleCategoryDetail bps){
		dao.updateBizPmSettleCategoryDetail(bps);
	}
	
	public QualityControlFineUpdateEntity getBizQcBillCheckItemById(String id){
		return dao.getBizQcBillCheckItemById(id);
	}
	@Transactional(readOnly = false)
	public void saveCheckitemPicAll(List<BusinessPic> pList){
		dao.saveCheckitemPicAll(pList);
	}
	public List<BusinessPic> findPicListByCheckItemId(String checkItemId){
		return dao.findPicListByCheckItemId(checkItemId);
	}
	public int findPicListCountByCheckItemId(String checkItemId){
		return dao.findPicListCountByCheckItemId(checkItemId);
	}
	
	@Transactional(readOnly = false)
	public void delImgById(String id){
		dao.delImgById(id);
	}
	
}
