
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;


@MyBatisDao
public interface BizMaterialSelfbuyReimburseDao extends CrudDao2<BizMaterialSelfbuyReimburse> {


	void deleteMaterialSelfbuyReimburse(Integer materialSelfbuyReimburseId);


	boolean updateMaterialSelfbuyReimburse(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);


	BizMaterialSelfbuyReimburse findMaterialAndOrderByMaterialId(Integer materialId);


	List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(Integer materialId);


	List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(
			ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog);
	
}