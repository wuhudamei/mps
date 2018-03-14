/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;

/**
 * 安装工主材问题上报
 */
@MyBatisDao
public interface InstallConstructBillProblemDao extends CrudDao2<BizOrderInstallItemProblemVo> {

	/**
	 * 安装工问题上报导出
	 * @param bizOrderInstallItemProblemVo
	 * @return
	 */
	List<BizOrderInstallItemProblemVo> findExport(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo);


	
}