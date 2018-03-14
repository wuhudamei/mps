
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderConstructionReport;


@MyBatisDao
public interface BizOrderConstructionReportDao extends CrudDao2<BizOrderConstructionReport>{

	List<BizOrderConstructionReport> getByStoreList(String storeID);
	
}