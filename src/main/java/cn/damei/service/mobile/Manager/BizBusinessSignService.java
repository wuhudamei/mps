/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.mobile.Manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Manager.BizBusinessSign;
import cn.damei.dao.mobile.Manager.BizBusinessSignDao;

/**
 * 人员签到Service
 * @author qww
 * @version 2016-11-03
 */
@Service
@Transactional(readOnly = true)
public class BizBusinessSignService extends CrudService2<BizBusinessSignDao, BizBusinessSign> {

	public BizBusinessSign get(Integer id) {
		return super.get(id);
	}
	
	/**
	 * 根据orderId、packId、signType、managerId查询项目经理验收时签到信息
	 * @param sign
	 * @return
	 */
	public BizBusinessSign queryEmployeeIsCheck(Integer employeeId, Integer packId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeId", employeeId);
		map.put("packId", packId);
		map.put("signType", ConstantUtils.SIGN_TYPE_MANAGER_CHECK);
		return dao.queryEmployeeIsCheck(map);
	}
	
	@Transactional(readOnly=false)
	public void insert(BizBusinessSign sign){
		dao.insert(sign);
	}
	
	@Transactional(readOnly=false)
	public void update(BizBusinessSign sign){
		dao.update(sign);
	}
	
	/**
	 * 通过订单Id查出基装验收的时间
	 * @param orderId
	 * @return
	 */
	public String getCheckDatetimeByOrderId(Integer orderId){
		return dao.getCheckDatetimeByOrderId(orderId);
	}
	
	public String getOrderIdBypPackId(Integer packId){
		return dao.getOrderIdBypPackId(packId);
	}
	
	public String getIsValidByOrderIdAndManagerIdAndSignDate(Map map){
		
		return dao.getIsValidByOrderIdAndManagerIdAndSignDate(map);
	}
	public void insertDayOrderByBizBusinessSign(BizBusinessSign sign){
		dao.insertDayOrderByBizBusinessSign(sign);
	}
	
	public void updateDayOrderByBizBusinessSign(BizBusinessSign sign){
		dao.updateDayOrderByBizBusinessSign(sign);
	}
}