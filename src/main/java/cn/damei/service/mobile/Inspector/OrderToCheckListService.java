package cn.damei.service.mobile.Inspector;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Inspector.OrderToCheckListDao;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.InspectorOrderVo;

/**
 * 质检系统约检
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class OrderToCheckListService extends CrudService2<OrderToCheckListDao, InspectorOrderVo> {

	// 根据质检员id 查询订单由项目经理申请约检的订单
	public List<InspectorOrderVo> findOrderByInspectorId(InspectorOrderVo vo) {
		return dao.findOrderByInspectorId(vo);
	}

	/**
	 * 得到订单经纬度
	 * 
	 * @param orderId
	 * @return
	 */
	public String getOrderLngLatByOrderId(Integer orderId) {

		return dao.getOrderLngLatByOrderId(orderId);
	}

	/**
	 * 质检员签到
	 * 
	 * @param detail
	 */
	@Transactional(readOnly = false)
	public void inspectorSign(InspectSign InspectSign) {

		dao.inspectorSign(InspectSign);
	}

	/**
	 * 该质检单是否有签到记录
	 * 
	 * @param inspectId
	 */
	public Integer findInspectSignRecord(Integer inspectId) {

		return dao.findInspectSignRecord(inspectId);
	}

	/**
	 * 更新质检员的签到记录
	 * 
	 * @param detail
	 */
	@Transactional(readOnly = false)
	public void updateInspectRecord(InspectSign detail) {

		dao.updateInspectRecord(detail);
	}
	/**
	 * 查询质检员还未对工人评价的数量
	 * @param orderId
	 * @param inspectorId
	 * @return
	 */
	public int noScoreCount(Integer orderId,Integer inspectorId) {
		return dao.noScoreCount(orderId, inspectorId);
	}
}
