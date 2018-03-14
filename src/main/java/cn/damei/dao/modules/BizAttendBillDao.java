/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAttendBill;

/**
 * 考勤单DAO接口
 * @author cgh
 */
@MyBatisDao
public interface BizAttendBillDao extends CrudDao2<BizAttendBill> {
	
	/**
	 * 未生成考勤单的list
	 * @param bizAttendBill
	 * @return
	 */
	public List<BizAttendBill> findNoBillList(BizAttendBill bizAttendBill);
	
	/**
	 * 生成考勤单的list
	 * @param bizAttendBill
	 * @return
	 */
	public List<BizAttendBill> findBillList(BizAttendBill bizAttendBill);
	
	/**
	 * 修改以生成考勤单批次id
	 * @param ids
	 * @param status
	 */
	public void updateStatus(@Param("ids")String[] ids,@Param("status") String status,@Param("attendBatchId")String attendBatchId);
	
	/**
	 * 根据批次id获取考勤单list
	 * @param batchId
	 * @return
	 */
	public List<BizAttendBill> findBizAttendBillListByBatchId(@Param("attendBatchId")Integer attendBatchId);
	
	/**
	 * 批量更新批次状态为0
	 * @param list
	 */
	public void updateBatchIds(List<BizAttendBill> list);
}