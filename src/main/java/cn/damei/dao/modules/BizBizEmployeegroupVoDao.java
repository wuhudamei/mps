/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeStarLog;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.OrderTaskpackage;

/**
 * 工人组管理DAO接口
 * 
 * @author qhy
 * @version 2016-09-01
 */
@MyBatisDao
public interface BizBizEmployeegroupVoDao extends CrudDao<BizEmployeegroupVO> {
	// 任务包，预留
	// public List<BizEmployeegroupVO>findGroupList();

	public List<BizEmployeegroupVO> findFreeLeader(BizEmployeegroupVO bizEmployeegroupVO);

	public List<OrderTaskpackage> findAllPackageWhomHasEmpGroups(List<BizEmployeegroupVO> list);

	public List<BizEmployeegroupVO> queryemployeegroup(BizEmployeegroupVO bizEmployeegroupVO);

	public BizEmployeegroupVO getbyId(BizEmployeegroupVO bizEmployeegroupVO);

	public void updateStar(Integer empGroupId);   //将数据更新至员工表的星级得分

	public List<BizEmployeegroupVO> getSumAvg(Integer empGroupId);    //获取总分 次数

	public void insertStarLog(double beforeScore, double afterScore,
			Integer empGroupId);

	public void updateStarGroup(Integer groupid);

	public List<BizEmployeeStarLog> selectChange(String groupid, Date startChangeDatetime, Date endChangeDatetime);

	public String selectRealName(String groupid);

	public List<BizEmployeegroupVO> selectChangeStarList(String groupid, Date startChangeDatetime, Date endChangeDatetime);

	public List<BizEmployeegroupVO> selectStarLog(Integer orderTaskpackageId);

	
}