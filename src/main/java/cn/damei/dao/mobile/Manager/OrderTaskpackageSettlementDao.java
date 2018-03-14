package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EmpTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageVo;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface OrderTaskpackageSettlementDao  extends CrudDao2<OrderTaskpackageSettlement>{
	/**
	 * 查询结算单确认验收信息
	 * @param id 任务包id
	 * @return
	 */
	public OrderTaskpackageVo queryTaskpackageSettlement(Integer id);

	/**
	 * 查询分配薪酬员工列表
	 * @param groupId
	 * @return
	 */
	public List<EmpTaskpackageSettlement> queryTaskpackageEmpDetail(Integer groupId);
	
	/**
	 * 更新结算单时，查询分配薪酬员工列表
	 * @param map
	 * @return
	 */
	public List<EmpTaskpackageSettlement> queryUpdateTaskpackageEmpDetail(Map<String, Object> map);
	
	/**
	 * 根据编号查结算单
	 * @param settlementNo
	 * @return
	 */
	public OrderTaskpackageSettlement queryTaskpackageSettlementByNo(String settlementNo);
	
	/**
	 * 根据任务包id查询结算单
	 * @param orderTaskpackageId
	 * @return
	 */
	public OrderTaskpackageSettlement queryTaskpackageSettlementByOrderTaskpackageId(Integer orderTaskpackageId);

	/**
	 * 任务包验收时查询质检罚款金额
	 * @param orderTaskpackageId
	 * @return
	 */
	public Double queryQcWorkerPublishAmountTotal(Integer orderTaskpackageId);
}