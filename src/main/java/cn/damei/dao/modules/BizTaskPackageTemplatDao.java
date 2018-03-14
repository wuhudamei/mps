
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizTaskPackageTemplat;


@MyBatisDao
public interface BizTaskPackageTemplatDao extends CrudDao<BizTaskPackageTemplat> {
	public List<BizTaskPackageTemplat>findTaskPackageList(String storeId);

	public BizTaskPackageTemplat getByprocedureNo(String procedureNo);
	
	public List<BizTaskPackageTemplat> queryTaskpackageTemplat();

	public BizTaskPackageTemplat getByNo(String no);

	public List<BizTaskPackageTemplat> getByNo1(String no);
	

	public List<BizTaskPackageTemplat> queryByStoreId(Integer storeid,Integer projectMode);

	public List<DropModel> findtaskpackageByStroeId(Integer storeid ,String status);

	public List<DropModel> findtaskpackageByStroeId1(String status);

	public List<BizTaskPackageTemplat> getTaskList(BizEmployee bizEmployee);


	public Integer checkEmpWorkType(BizTaskPackageTemplat bizTaskPackageTemplat);
}