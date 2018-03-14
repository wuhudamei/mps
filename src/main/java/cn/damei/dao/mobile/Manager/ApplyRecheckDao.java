package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyRecheckEntity;

@MyBatisDao
public interface ApplyRecheckDao {

	
	

	public List<ApplyRecheckEntity> findRecheckList(Integer managerId);
	
	
	

	public List<ApplyRecheckEntity> selectCheckItemByRecheckId(Integer reCheckId);
	
	
	

	public void savePic(ReportCheckDetailsPic pic);
	
	

	public void updateRecheckStatus(ApplyRecheckEntity  entity);
	
}
