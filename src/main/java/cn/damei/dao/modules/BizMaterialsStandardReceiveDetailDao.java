
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;


@MyBatisDao
public interface BizMaterialsStandardReceiveDetailDao extends CrudDao2<BizMaterialsStandardReceiveDetail> {

	List<BizMaterialsStandardReceiveDetail> findDetailsByBillId(Integer materialsStandardReceiveBillId);

	List<BizMaterialsStandardReceiveDetail> findDetailsBySettleBillId(Integer billId);

	void insert1(List<BizMaterialsStandardReceiveDetail> insertList);

	void update1(List<BizMaterialsStandardReceiveDetail> updateList);

	void updateDetails(Integer materialsId, Integer billId, Double materialsAmount,Double number);

	void updateDtaileSnape(BizMaterialsStandardReceiveDetail detail);

	void updateDtaileApplySnape(String billId);

	String getOrderId(String billId);
}