/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;

/**
 * 墙地砖问题上报
 */
@MyBatisDao
public interface BusinessWallAndFloorProblemDao extends CrudDao2<BizOrderInstallItemProblemVo> {

	/**
	 * 墙地砖问题上报详情页
	 * @param problemId
	 * @return
	 */
	BizOrderInstallItemProblemVo findDetails(Integer problemId);

	/**
	 * 更新问题上报状态
	 * @param bizOrderInstallItemProblem
	 * @return
	 */
	boolean updateProblem(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	
}