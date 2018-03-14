package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;

/**
 * 约检列表确认验收
 * @author Administrator
 *
 */
@MyBatisDao
public interface CheckConfirmDao extends CrudDao2<CheckConfirm>{

	//通过质检单id查询质检单
	CheckConfirm findQcBillById(int id);

	//保存验收日期
	void updateInform(CheckConfirm checkConfirm);

	//保存图片到数据库
	void savePic(ReportCheckDetailsPic reportCheckDetailsPic);

	//根据质检单id查询约检节点是否与付款单结算节点关联
	Integer findCheckNodeRel(Integer id);

	//根据约检节点id查询是否与项目经理结算节点关联
	Integer findSettleNodeRel(Integer qcCheckNodeId);

	//批量插入约检验收图片
	void savePicAll(List<ReportCheckDetailsPic> pList);

	/**
	 * 结算（工人，项目经理，质检员）
	 * @param id
	 * @return
	 */
	CheckConfirm findSettlement(Integer id);
	void updateOrderActualEndDateByCheckConfirm(CheckConfirm checkConfirm);
}
