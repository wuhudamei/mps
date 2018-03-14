/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.WallFloorReturn;

/**
 * 墙地砖退货DAO接口
 * 
 * @author 张同维
 * @version 2017-09-26
 */
@MyBatisDao
public interface WallFloorReturnDao extends CrudDao<WallFloorReturn> {

	List<WallFloorReturn> findList30(WallFloorReturn wallFloorReturn);

}