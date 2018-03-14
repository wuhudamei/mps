package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.InspectorOrderVo;

/**
 * 质检系统约检
 * 
 * @author Administrator
 *
 */
@MyBatisDao
public interface OrderToCheckListDao extends CrudDao2<InspectorOrderVo> {

	// 根据质检员id(搜索文本) 查询由项目经理发起的质检单List
	List<InspectorOrderVo> findOrderByInspectorId(InspectorOrderVo vo);

	// 订单经纬度
	public String getOrderLngLatByOrderId(Integer orderId);

	/**
	 * 质检员签到
	 * 
	 * @param detail
	 */
	public void inspectorSign(InspectSign inspectSign);

	/**
	 * 该质检单是否有签到记录
	 * 
	 * @param inspectId
	 */
	public Integer findInspectSignRecord(Integer inspectId);

	/**
	 * 更新质检员签到记录
	 * 
	 * @param detail
	 */
	public void updateInspectRecord(InspectSign InspectSign);
	//查询
	public int noScoreCount(Integer orderId,Integer inspectorId);
}
