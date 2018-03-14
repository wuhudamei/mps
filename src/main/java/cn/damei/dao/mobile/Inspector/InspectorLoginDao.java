package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.Inspector;


@MyBatisDao
public interface InspectorLoginDao   extends CrudDao2<Inspector> {
	

	Inspector selectInspectorByPhone(String phone);
	

	public int findCount(Integer inspectorId);

	public int findBuildingCount(Integer inspectorId);
	public Integer	findInspectReport(Integer inspectorId);


	Integer findEvalCount(Inspector inspector);
	Integer findView(Inspector inspector);
	public Integer isLeader(Integer id);

	Inspector selectInspectorByPhone1(String username, String string);
}
