package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.InspectorOrderVo;


@MyBatisDao
public interface OrderToCheckListDao extends CrudDao2<InspectorOrderVo> {


	List<InspectorOrderVo> findOrderByInspectorId(InspectorOrderVo vo);


	public String getOrderLngLatByOrderId(Integer orderId);


	public void inspectorSign(InspectSign inspectSign);


	public Integer findInspectSignRecord(Integer inspectId);


	public void updateInspectRecord(InspectSign InspectSign);

	public int noScoreCount(Integer orderId,Integer inspectorId);
}
