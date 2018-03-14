package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProblemItemReport;

@MyBatisDao
public interface ProblemItemReportDao extends CrudDao<ProblemItemReport> {

	List<ProblemItemReport> queryItemList(ProblemItemReport problemItemReport);

	List<ProblemItemReport> queryWorkTypeList(ProblemItemReport problemItemReport);

	List<ProblemItemReport> queryRegionList(ProblemItemReport problemItemReport);

	List<ProblemItemReport> queryRegionCount(ProblemItemReport problemItemReport);

	List<ProblemItemReport> queryWorkTypeCount(ProblemItemReport problemItemReport);

}
