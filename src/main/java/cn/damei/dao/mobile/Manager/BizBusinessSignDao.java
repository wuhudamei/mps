/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizBusinessSign;

/**
 * 人员签到DAO接口
 * @author qww
 * @version 2016-11-03
 */
@MyBatisDao
public interface BizBusinessSignDao extends CrudDao2<BizBusinessSign> {
	
	/**
	 * 根据orderId、packId、signType、managerId查询项目经理验收时签到信息
	 * @param sign
	 * @return
	 */
	public BizBusinessSign queryEmployeeIsCheck(Map<String, Object> map);
	public String getCheckDatetimeByOrderId(Integer orderId);
	public String getOrderIdBypPackId(Integer packId);
	public String getIsValidByOrderIdAndManagerIdAndSignDate(Map map);
	public void insertDayOrderByBizBusinessSign(BizBusinessSign sign);
	
	public void updateDayOrderByBizBusinessSign(BizBusinessSign sign);
}