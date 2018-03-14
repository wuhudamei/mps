/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.RecheckCnfg;

/**
 * 复尺配置表DAO接口
 * 
 * @author ztw
 * @version 2017-08-02
 */
@MyBatisDao
public interface RecheckCnfgDao extends CrudDao<RecheckCnfg> {

	/**
	 * 查询墙地砖复尺配置表（材料单价和面积超出上限比例）
	 * 
	 * @return
	 */
	RecheckCnfg findRecheckCnfgMessage();

	List<RecheckCnfg> findAllListz();

	void deleteall(RecheckCnfg recheckCnfg);

}