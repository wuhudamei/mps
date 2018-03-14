/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;

/**
 * 结算单DAO接口
 * @author 汪文文
 * @version 2016-10-14
 */
@MyBatisDao
public interface BizOrderTaskpackageSettlementDao extends CrudDao2<BizOrderTaskpackageSettlement> {

	BizOrderTaskpackageSettlement findByOrderTaskpackageId(Integer orderTaskpackageId);

	void updateSettlementStatus(Integer taskPackageId, String status);
	
	void updateSettlementStatusAndDate(Integer taskPackageId, String status, Date date);
	
	List<BizOrderTaskpackageSettlement> getPassOrderTaskPageSettle(Map<String,Object> param);
	
}