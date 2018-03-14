
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEngineeringDepartment;


@MyBatisDao
public interface BizEngineeringDepartmentDao extends CrudDao<BizEngineeringDepartment> {
    
    public List<
    
    DropModel> findEngDepList();
    public List<DropModel> findEngDepListWithUserConditions(List<Integer> ids);

    public List<DropModel> findEngDepListOnlyIndustry();
    public List<BizEngineeringDepartment> findByStoreId(String storeId);
	public List<DropModel> findEngDepListByStoreId(String storeId);
	public List<DropModel> findEngDepListByStoreId1();
	public List<DropModel> findAllEngDepList();

	public List<DropModel> findEngDepListByMap(Map<String, Object> map);
	public List<Integer> findAll();
	public List<Integer> findByEmployeeId(Integer employeeId);



	public List<DropModel> findCheckNodeByStoreIdAndProjectModel(String storeId,String projectModel);

}