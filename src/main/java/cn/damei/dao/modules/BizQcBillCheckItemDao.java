
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcBillCheckItem;

import java.util.List;


@MyBatisDao
public interface BizQcBillCheckItemDao extends CrudDao<BizQcBillCheckItem> {

    List<BizQcBillCheckItem> queryEmployeeAndgroupIdinfo(BizQcBillCheckItem pmPunishEmployee);
}