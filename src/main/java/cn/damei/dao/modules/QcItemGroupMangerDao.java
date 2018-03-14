
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.QcItemGroupManger;

import java.util.List;


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