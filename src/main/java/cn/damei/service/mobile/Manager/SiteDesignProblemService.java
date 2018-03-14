package cn.damei.service.mobile.Manager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.dao.mobile.Manager.SiteDesignProblemDao;


/**
 * 工地设计问题上报Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class SiteDesignProblemService {

	@Autowired
	private SiteDesignProblemDao dao;

	/**
	 * 工地设计问题上报订单列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	public List<MaterialManagement> findOrderList(Integer managerId, String text) {
		MaterialManagement materialManagement = new MaterialManagement();
		//项目经理
		materialManagement.setItemManagerId(managerId);
		//搜索文本框
		materialManagement.setText(text);
		//订单状态
		materialManagement.setOrderStatusNumber(OrderConstantUtil.ORDER_STATUS_SETTLEMENT_CHECK_PASS_340);
		
		return dao.findOrderList(materialManagement);
	}

	/**
	 * 工地设计问题上报 详情列表
	 * @param orderId
	 * @param businessProblemBusinessType
	 * @param businessProblemSolveRole
	 * @param businessProblemStatus
	 * @param businessType
	 * @return
	 */
	public List<Map<String, String>> findProblemLogList(String orderId, String businessProblemBusinessType,
			String businessProblemSolveRole, String businessProblemStatus,String businessType) {
		
		Map<String,Object> map = new HashMap<>();
		//订单id
		map.put("orderId",orderId);
		//业务类型
		map.put("businessProblemBusinessType",businessProblemBusinessType);
		//上报日志角色
		map.put("businessProblemSolveRole",businessProblemSolveRole);
		//上报日志状态
		map.put("businessProblemStatus",businessProblemStatus);
		//图片类型
		map.put("businessType",businessType);
		
		return dao.findProblemLogList(map);
	}


}
