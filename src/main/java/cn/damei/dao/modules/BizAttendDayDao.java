/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAttendDay;

/**
 * 考勤日次表DAO接口
 * @author cgh
 */
@MyBatisDao
public interface BizAttendDayDao extends CrudDao2<BizAttendDay> {
	/**
	 * 任务包验收签到
	 * @return
	 */
	public List<BizAttendDay> selectBusinessSigns();
	
	/**
	 * 现场签到
	 * @return
	 */
	public List<BizAttendDay> selectSigns();
	
	/**
	 * insert
	 * @param list
	 * @return
	 */
	public int insertAttend(List<BizAttendDay> list);
	
	/**
	 * findAttendDays
	 * @param attendEmployeeId
	 * @param attendMonth
	 * @return
	 */
	public List<BizAttendDay> findAttendDays(@Param("attendEmployeeId")Integer attendEmployeeId,@Param("attendMonth")String attendMonth);
	
	/**
	 * 获取根据经理id的list
	 * @param attendEmployeeId
	 * @return
	 */
	public List<BizAttendDay> findAttendDaysByEmployeeId(Integer attendEmployeeId);
	
	/**
	 * 批量更新状态
	 * @param list
	 */
	public void upadteAttendDays(List<BizAttendDay> list);
	
}