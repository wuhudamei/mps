package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;


@MyBatisDao
public interface CheckConfirmDao extends CrudDao2<CheckConfirm>{


	CheckConfirm findQcBillById(int id);


	void updateInform(CheckConfirm checkConfirm);


	void savePic(ReportCheckDetailsPic reportCheckDetailsPic);


	Integer findCheckNodeRel(Integer id);


	Integer findSettleNodeRel(Integer qcCheckNodeId);


	void savePicAll(List<ReportCheckDetailsPic> pList);


	CheckConfirm findSettlement(Integer id);
	void updateOrderActualEndDateByCheckConfirm(CheckConfirm checkConfirm);
}
