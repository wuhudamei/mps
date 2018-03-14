package cn.damei.dao.mobile.Manager;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface GuaranteeMoneyDao  extends CrudDao2<GuaranteeMoney>{
	
	/**
	 * 查询工人组长扣质保金次数
	 * @param map
	 * @return
	 */
	public Integer queryGuaranteeMoneyCount(Map<String, Object> map);
	
	/**
	 * 查询工人组长所扣质保金累积
	 * @param map
	 * @return
	 */
	public Double queryGuaranteeMoneySum(Map<String, Object> map);
	
	/**
	 * 根据任务包id和结算单id查质保金表
	 * @param map
	 * @return
	 */
	public GuaranteeMoney queryGuarnteeMoney(Map<String, Object> map);
	
	/**
	 * 修改质保金
	 * @param money
	 * @return
	 */
	public int updateGuaranteeMoney(GuaranteeMoney money);

	/**
	 * 根据任务包id查询质保金表
	 * @param orderTaskpackageId
	 * @return
	 */
	public GuaranteeMoney queryGuarnteeMoneyByTaskId(Integer orderTaskpackageId);
}