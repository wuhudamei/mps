
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeStarLog;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.OrderTaskpackage;


@MyBatisDao
public interface BizBizEmployeegroupVoDao extends CrudDao<BizEmployeegroupVO> {



	public List<BizEmployeegroupVO> findFreeLeader(BizEmployeegroupVO bizEmployeegroupVO);

	public List<OrderTaskpackage> findAllPackageWhomHasEmpGroups(List<BizEmployeegroupVO> list);

	public List<BizEmployeegroupVO> queryemployeegroup(BizEmployeegroupVO bizEmployeegroupVO);

	public BizEmployeegroupVO getbyId(BizEmployeegroupVO bizEmployeegroupVO);

	public void updateStar(Integer empGroupId);

	public List<BizEmployeegroupVO> getSumAvg(Integer empGroupId);

	public void insertStarLog(double beforeScore, double afterScore,
			Integer empGroupId);

	public void updateStarGroup(Integer groupid);

	public List<BizEmployeeStarLog> selectChange(String groupid, Date startChangeDatetime, Date endChangeDatetime);

	public String selectRealName(String groupid);

	public List<BizEmployeegroupVO> selectChangeStarList(String groupid, Date startChangeDatetime, Date endChangeDatetime);

	public List<BizEmployeegroupVO> selectStarLog(Integer orderTaskpackageId);

	
}