
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizAttendDay;


@MyBatisDao
public interface BizAttendDayDao extends CrudDao2<BizAttendDay> {

	public List<BizAttendDay> selectBusinessSigns();
	

	public List<BizAttendDay> selectSigns();
	

	public int insertAttend(List<BizAttendDay> list);
	

	public List<BizAttendDay> findAttendDays(@Param("attendEmployeeId")Integer attendEmployeeId,@Param("attendMonth")String attendMonth);
	

	public List<BizAttendDay> findAttendDaysByEmployeeId(Integer attendEmployeeId);
	

	public void upadteAttendDays(List<BizAttendDay> list);
	
}