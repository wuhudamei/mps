
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProjectInstallItem;


@MyBatisDao
public interface BizProjectInstallItemDao extends CrudDao<BizProjectInstallItem> {


	public void isON(BizProjectInstallItem installItem);

	public List<BizProjectInstallItem> queryInstallItemList(BizProjectInstallItem bizProjectInstallItem);
}