/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 员工信息DAO接口
 * @author qhy
 * @version 2016-08-24
 */
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
	
	/**
	 * 查询员工被换单次数
	 * @param id
	 * @return
	 */
	public BizEmployee selectExchangeOrderTimesById(Integer id);
	
	/**
	 * 更新被换单次数
	 */
	public void updateExchangeOrderTimes(BizEmployee bizEmployee);
	
	/**
	 * 工人list
	 * @param bizEmployee
	 * @return
	 */
	public List<BizEmployee> findLeadList(BizEmployee bizEmployee);
	
	/**
	 * 项目经理 list 换单查询使用
	 * @param bizEmployee
	 * @return
	 */
	public List<BizEmployee> findExCahangeManagerList(BizEmployee bizEmployee);
	/**
	 * 质检员 换单查询使用
	 * @param bizEmployee
	 * @return
	 */
	public List<BizEmployee> findExCahangeInspectorList(BizEmployee bizEmployee);

	public List<BizEmployee> getEmployeeListByEmpType(Map<String, String> paramaterMap);

	public int getEmployeeCount(Map<String, String> paramaterMap);
	/**
	 * 更新员工信息
	 * @param emp
	 */
	public void updateEmployee(BizEmployee emp);

    Office findStoreLabel(String empId);
}