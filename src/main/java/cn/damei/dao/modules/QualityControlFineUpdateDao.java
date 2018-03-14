package cn.damei.dao.modules;


import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.entity.modules.QualityControlFineUpdateEntity;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;

@MyBatisDao
public interface QualityControlFineUpdateDao   extends CrudDao<QualityControlFineUpdateEntity>{
	public String findSettleStatus(BizPmSettleCategoryDetail bps);
	public void updateBizQcBillCheckItem(QualityControlFineUpdateEntity qc);
	public void insertBizQcBillCheckItemLog(Map<String,String> map);
	public void updateBizPmSettleCategoryDetail(BizPmSettleCategoryDetail bps);
	public QualityControlFineUpdateEntity getBizQcBillCheckItemById(String id);
	public void saveCheckitemPicAll(List<BusinessPic> pList);
	public List<BusinessPic> findPicListByCheckItemId(String qcCheckItemId);
	public int findPicListCountByCheckItemId(String qcCheckItemId);
	public void delImgById(String id);
}
