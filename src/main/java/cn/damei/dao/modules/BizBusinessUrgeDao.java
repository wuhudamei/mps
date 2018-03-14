/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.entity.modules.InstallBusinessUrge;
import cn.damei.entity.modules.WallAndFloorBusinessUrge;

/**
 * 业务催促表DAO接口
 * @author wyb
 * @version 2017-05-03
 */
@MyBatisDao
public interface BizBusinessUrgeDao extends CrudDao2<BizBusinessUrge> {

	/**
	 *  催促安装，一天最多允许催促次数
	 * @param bizBusinessUrge
	 * @return
	 */
	Integer findCount(BizBusinessUrge bizBusinessUrge);

	/**
	 * 催促回复5分钟校验
	 * @param bizBusinessUrge
	 * @return
	 */
	Integer findCountByfiveTime(BizBusinessUrge bizBusinessUrge);

	/**
	 * 主材催促查询
	 * @param installBusinessUrge
	 * @return
	 */
	List<InstallBusinessUrge> findInstallSelectList(InstallBusinessUrge installBusinessUrge);

	/**
	 * 墙地砖催促查询
	 * @param bizBusinessUrge
	 * @return
	 */
	List<WallAndFloorBusinessUrge> findWallAndFloorSelectList(WallAndFloorBusinessUrge wallAndFloorBusinessUrge);


    List<BizBusinessUrge> findUnqualifiedAcceptLog(Map<String, String> map);
}