package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ProjectManagerSettlement;

@MyBatisDao
public interface PreSettleBilllogDao extends CrudDao2<ProjectManagerSettlement>{

	String findBusinessOnlyMark(ProjectManagerSettlement preSettleBilllog);

	List<String> findQualityId(ProjectManagerSettlement preSettleBilllog);

	List<String> findOrderFinishBill(ProjectManagerSettlement preSettleBilllog);

}
