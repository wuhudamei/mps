package cn.damei.dao.mobile.Manager;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;


@MyBatisDao
public interface GuaranteeMoneyDao  extends CrudDao2<GuaranteeMoney>{
	

	public Integer queryGuaranteeMoneyCount(Map<String, Object> map);
	

	public Double queryGuaranteeMoneySum(Map<String, Object> map);
	

	public GuaranteeMoney queryGuarnteeMoney(Map<String, Object> map);
	

	public int updateGuaranteeMoney(GuaranteeMoney money);


	public GuaranteeMoney queryGuarnteeMoneyByTaskId(Integer orderTaskpackageId);
}