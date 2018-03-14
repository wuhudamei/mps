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



@Service
@Transactional(readOnly = true)
public class SiteDesignProblemService {

	@Autowired
	private SiteDesignProblemDao dao;


	public List<MaterialManagement> findOrderList(Integer managerId, String text) {
		MaterialManagement materialManagement = new MaterialManagement();

		materialManagement.setItemManagerId(managerId);

		materialManagement.setText(text);

		materialManagement.setOrderStatusNumber(OrderConstantUtil.ORDER_STATUS_SETTLEMENT_CHECK_PASS_340);
		
		return dao.findOrderList(materialManagement);
	}


	public List<Map<String, String>> findProblemLogList(String orderId, String businessProblemBusinessType,
			String businessProblemSolveRole, String businessProblemStatus,String businessType) {
		
		Map<String,Object> map = new HashMap<>();

		map.put("orderId",orderId);

		map.put("businessProblemBusinessType",businessProblemBusinessType);

		map.put("businessProblemSolveRole",businessProblemSolveRole);

		map.put("businessProblemStatus",businessProblemStatus);

		map.put("businessType",businessType);
		
		return dao.findProblemLogList(map);
	}


}
