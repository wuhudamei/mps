/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.SysSequence;

/**
 * 编号序列管理DAO接口
 * @author 魏建勇
 * @version 2016-08-21
 */
@MyBatisDao
public interface SysSequenceDao extends CrudDao<SysSequence> {
    public String getSequence(String orderNo);
}