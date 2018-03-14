/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.QcItemGroupManger;

import java.util.List;

/**
 * 检查项工人组和项目经理DAO接口
 * @author ztw
 * @version 2017-12-24
 */
@MyBatisDao
public interface QcItemGroupMangerDao extends CrudDao<QcItemGroupManger> {

    List<QcItemGroupManger> queryWorkerGrouPunishCountAndIllegal(QcItemGroupManger qcItemGroupManger);

    List<QcItemGroupManger> findManagerPage(QcItemGroupManger qcItemGroupManger);
    List<QcItemGroupManger> queryMaPunishCountAndIllegal(QcItemGroupManger qcItemGroupManger);

    List<QcItemGroupManger> queryQcItemMangerIllegalDetails(QcItemGroupManger qcItemGroupManger);

    List<QcItemGroupManger> queryQcItemMangerPunishDetails(QcItemGroupManger qcItemGroupManger);

    List<QcItemGroupManger> queryQcItemGroupIllegalDetails(QcItemGroupManger qcItemGroupManger);

    List<QcItemGroupManger> queryQcItemGroupPunishDetails(QcItemGroupManger qcItemGroupManger);
}