/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcBillCheckItem;

import java.util.List;

/**
 * 质检检查项查询DAO接口
 * @author ztw
 * @version 2017-12-21
 */
@MyBatisDao
public interface BizQcBillCheckItemDao extends CrudDao<BizQcBillCheckItem> {

    List<BizQcBillCheckItem> queryEmployeeAndgroupIdinfo(BizQcBillCheckItem pmPunishEmployee);
}