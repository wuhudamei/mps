
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmpArea;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.Office;


@MyBatisDao
public interface BizEmployeeDao extends CrudDao<BizEmployee> {

	public int getCountByNo(BizEmployee employee);
	
	public int getCountByLoginName(BizEmployee employee);
	public List<BizEmpStore>findStoreList();
	public List<BizEmpArea>findAreaByStoreId(String store,String projectMode);
	public List<DropModel>findEmployeeList(BizEmployee employee);
	
	public List<DropModel>findEmployeeListByCondition(Map<String,String> map);
	
	public List<BizEmployee>findListNotInGroup();
	
	public List<DropModel>findAllListByIds(Map<String,String> map);
	public int getCountByPhone(BizEmployee employee);

	public List<DropModel> findLeaderListByCondition(Map map);

	public List<DropModel> findLeaderList(BizEmployee employee);

	public List<DropModel> findEmployeeListByEmpType(Map<String,Object> map);

	public int getCountByIdcardno(BizEmployee employee);

	public List<DropModel> findAllListByDepartmentId(Map<Object,String> map);
	
	public List<Integer> findEngineIdsByEmpId(Integer empId);

	public List<BizEmployee> findManagerList(BizEmployee bizEmployee);

	public List<BizEmployee> findInspectorList(BizEmployee bizEmployee);
	
	public List<BizEmployee> findWorkGroupInfoByOrderId(Integer orderId);
	
	public List<BizEmployee> findItemManagerInfoByOrderId(Integer orderId);
	

	public BizEmployee selectExchangeOrderTimesById(Integer id);
	

	public void updateExchangeOrderTimes(BizEmployee bizEmployee);
	

	public List<BizEmployee> findLeadList(BizEmployee bizEmployee);
	

	public List<BizEmployee> findExCahangeManagerList(BizEmployee bizEmployee);

	public List<BizEmployee> findExCahangeInspectorList(BizEmployee bizEmployee);

	public List<BizEmployee> getEmployeeListByEmpType(Map<String, String> paramaterMap);

	public int getEmployeeCount(Map<String, String> paramaterMap);

	public void updateEmployee(BizEmployee emp);

    Office findStoreLabel(String empId);
}