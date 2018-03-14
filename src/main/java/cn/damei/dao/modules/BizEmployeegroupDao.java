/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeegroup;

/**
 * 工人组管理DAO接口
 * @author qhy
 * @version 2016-09-01
 */
@MyBatisDao
public interface BizEmployeegroupDao extends CrudDao<BizEmployeegroup> {
	//任务包，预留
//	public List<BizEmployeegroupVO>findGroupList();
	
	/**
     * 检查工人是否已经在某个工人组里了
     * @author chaowang
     * @date 2016年10月11日
     * @param param 员工id
     * @return
     */
	public int hasInGroup(Map<String,Object> param);

	public BizEmployeegroup findBizEmployeegroup(String string);
	
	public void updateStarLog(String groupid, Integer integer, String string, Integer integer2);  //更新星级变化记录表中的变更原因类型和说明
}