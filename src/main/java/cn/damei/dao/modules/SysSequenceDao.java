
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.SysSequence;


@MyBatisDao
public interface SysSequenceDao extends CrudDao<SysSequence> {
    public String getSequence(String orderNo);
}