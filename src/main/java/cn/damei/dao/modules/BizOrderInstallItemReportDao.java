
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderCheckSize;
import cn.damei.entity.modules.BizOrderInstallItemReport;
import cn.damei.entity.modules.BizOrderInstasllPlanAndProblem;


@MyBatisDao
public interface BizOrderInstallItemReportDao extends CrudDao2<BizOrderInstallItemReport> {


	List<BizOrderInstallItemReport> findOrderMessage();


	boolean batchUpdateList(List<BizOrderInstallItemReport> mixUpdateList);


	boolean batchInsertList(List<BizOrderInstallItemReport> mixInsertListList);


	List<BizOrderCheckSize> findCheckSizeMessage(Integer orderId);


	List<BizOrderInstasllPlanAndProblem> findInstallPlanAndProblemMessage(Integer orderId);

	
}