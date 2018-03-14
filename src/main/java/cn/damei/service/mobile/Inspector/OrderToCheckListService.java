package cn.damei.service.mobile.Inspector;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Inspector.OrderToCheckListDao;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.InspectorOrderVo;


@Service
@Transactional(readOnly = true)
public class OrderToCheckListService extends CrudService2<OrderToCheckListDao, InspectorOrderVo> {


	public List<InspectorOrderVo> findOrderByInspectorId(InspectorOrderVo vo) {
		return dao.findOrderByInspectorId(vo);
	}


	public String getOrderLngLatByOrderId(Integer orderId) {

		return dao.getOrderLngLatByOrderId(orderId);
	}


	@Transactional(readOnly = false)
	public void inspectorSign(InspectSign InspectSign) {

		dao.inspectorSign(InspectSign);
	}


	public Integer findInspectSignRecord(Integer inspectId) {

		return dao.findInspectSignRecord(inspectId);
	}


	@Transactional(readOnly = false)
	public void updateInspectRecord(InspectSign detail) {

		dao.updateInspectRecord(detail);
	}

	public int noScoreCount(Integer orderId,Integer inspectorId) {
		return dao.noScoreCount(orderId, inspectorId);
	}
}
