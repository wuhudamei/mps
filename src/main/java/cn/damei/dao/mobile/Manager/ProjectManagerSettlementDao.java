package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ProjectManagerSettlement;
@MyBatisDao
public interface ProjectManagerSettlementDao extends CrudDao2<ProjectManagerSettlement>{

	void updateManagerSettlement(ProjectManagerSettlement projectManagerSettlement);

	ProjectManagerSettlement findSettlement(String orderId, int i);

	List<ProjectManagerSettlement> findSettlementEndList(ProjectManagerSettlement projectManagerSettlement);

	ProjectManagerSettlement findSettlementEndDetails(ProjectManagerSettlement projectManagerSettlement);

}
