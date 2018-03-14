
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.damei.common.utils.ConstantUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmpArea;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.dao.modules.BizEmployeeDao2;
import cn.damei.dao.modules.UserDao;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.User;


@Service
@Transactional(readOnly = true)
public class BizEmployeeService extends
		CrudService<BizEmployeeDao, BizEmployee> {

	@Resource
	private BizEmployeeDao bizEmployeeDao;
	@Resource
	private UserDao userDao;
	@Autowired
	private BizEmployeeDao2 bizEmployeeDao2;

	public int getCountByNo(BizEmployee employee) {
		return bizEmployeeDao.getCountByNo(employee);
	}
	public int getCountByLoginName(BizEmployee employee) {
		return bizEmployeeDao.getCountByLoginName(employee);
	}
	public List<BizEmpArea>findAreaByStoreId(String store,String projectMode)
	{
		return bizEmployeeDao.findAreaByStoreId(store,projectMode);
	}
	public BizEmployee get(String id) {
		return super.get(id);
	}

	public List<BizEmployee> findList(BizEmployee bizEmployee) {
		return super.findList(bizEmployee);
	}

	public Page<BizEmployee> findPage(Page<BizEmployee> page,
			BizEmployee bizEmployee) {
		page.setOrderBy(" storename,no asc");
		return super.findPage(page, bizEmployee);
	}
	

	public Page<BizEmployee> findLeadListPage(Page<BizEmployee> page,BizEmployee bizEmployee){
		bizEmployee.setPage(page);
		page.setList(bizEmployeeDao.findLeadList(bizEmployee));
		return page;
	}
	

	public Page<BizEmployee> findExCahangeManagerList(Page<BizEmployee> page,BizEmployee bizEmployee){
		bizEmployee.setPage(page);
		page.setList(bizEmployeeDao.findExCahangeManagerList(bizEmployee));
		return page;
	}
	

	public Page<BizEmployee> findExCahangeInspectorList(Page<BizEmployee> page,BizEmployee bizEmployee){
		bizEmployee.setPage(page);
		page.setList(bizEmployeeDao.findExCahangeInspectorList(bizEmployee));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(BizEmployee bizEmployee) {
		bizEmployee.setHeadpic(bizEmployee.getHeadpic().replace("|", ""));
		boolean isAdd = bizEmployee.getIsNewRecord();
		super.save(bizEmployee);
		if (isAdd) {

		User user=new User();
		user.preInsert();
		user.setName(bizEmployee.getRealname());
		user.setLoginName(bizEmployee.getLoginname());
		user.setPassword(SystemService.entryptPassword("111111"));
		user.setOffice(new Office(bizEmployee.getStoreid()));
		user.setCompany(new Office("1"));
		user.setNo(bizEmployee.getNo());
		user.setEmpId(bizEmployee.getId());
		user.setPhone(bizEmployee.getPhone());
		userDao.insert(user);
		} else{
			User user = userDao.findUserByEmpId(Integer.parseInt(bizEmployee.getId()));
			user.preUpdate();
			user.setName(bizEmployee.getRealname());
			user.setLoginName(bizEmployee.getLoginname());


			user.setNo(bizEmployee.getNo());


			user.setPhone(bizEmployee.getPhone());
			userDao.update(user);
		}
	}

	@Transactional(readOnly = false)
	public void delete(BizEmployee bizEmployee) {
		
		BizEmployee bizEmployee2 = super.get(bizEmployee.getId());

		User user = new User();
		user.setId(bizEmployee2.getSysuserid());
		userDao.delete(user);

		super.delete(bizEmployee);

		List<String> leader = bizEmployeeDao2.findIsLeader(Integer.parseInt(bizEmployee2.getId()));
		if(leader != null && leader.size() > 0){
			BizEmployee2 bizEmployee3 = new BizEmployee2();
			bizEmployee3.setDelFlag("1");
			bizEmployee3.setId(Integer.parseInt(bizEmployee2.getId()));
			bizEmployeeDao2.updateGroupState(bizEmployee3);
		}
		
	}
	public int getCountByPhone(BizEmployee employee) {
		return bizEmployeeDao.getCountByPhone(employee);
	}
	public int getCountByIdcardno(BizEmployee employee) {
		return bizEmployeeDao.getCountByIdcardno(employee);
	}
	public List<DropModel>findEmployeeListByCondition(Map map)
	{



		return bizEmployeeDao.findEmployeeListByCondition(map);
	}
	public List<BizEmployee>findListNotInGroup()
	{
		return bizEmployeeDao.findListNotInGroup();
	}
	@Transactional(readOnly = false)
	public void update(BizEmployee bizEmployee) {
		bizEmployee.preUpdate();
		dao.update(bizEmployee);
	}
	@Transactional(readOnly = false)
	public void insert(BizEmployee bizEmployee) {
		bizEmployee.preInsert();
		dao.insert(bizEmployee);
	}
	public List<DropModel> findLeaderListByCondition(Map map) {

		return bizEmployeeDao.findLeaderListByCondition(map);
	}

	public List<DropModel> employeeByCondition(String storeId, String empType, String projectMode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("officeIds", storeId);
		map.put("empTypes", empType);
		map.put("projectMode", projectMode);
		return dao.findEmployeeListByCondition(map);
	}

	public List<DropModel> findEmployeeListByEmpType(Integer storeId, String projectMode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		List<String> projectModeList = new ArrayList<String>();
		if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(projectMode)){
			projectModeList.add(ConstantUtils.EMPLOYEE_PROJECT_MODE_1);
			projectModeList.add(ConstantUtils.EMPLOYEE_PROJECT_MODE_2);
			projectModeList.add(ConstantUtils.EMPLOYEE_PROJECT_MODE_3);
		}else{
			projectModeList.add(projectMode);
			projectModeList.add(ConstantUtils.EMPLOYEE_PROJECT_MODE_3);
		}
		map.put("projectModeList", projectModeList);
		return dao.findEmployeeListByEmpType(map);
	}
	public Page<BizEmployee> findManagerPage(Page<BizEmployee> page, BizEmployee bizEmployee) {
		bizEmployee.setPage(page);
		page.setList(dao.findManagerList(bizEmployee));
		page.setOrderBy(" storename,no asc");
		return page;
	}
	public Page<BizEmployee> findInspectorPage(Page<BizEmployee> page, BizEmployee bizEmployee) {
		bizEmployee.setPage(page);
		page.setList(dao.findInspectorList(bizEmployee));
		page.setOrderBy(" storename,no asc");
		return page;
	}
	
	public List<BizEmployee> findWorkGroupInfoByOrderId(Integer orderId){
	    return dao.findWorkGroupInfoByOrderId(orderId);
	}
	
	public List<BizEmployee> findItemManagerInfoByOrderId(Integer orderId){
		return dao.findItemManagerInfoByOrderId(orderId);
	}
	

	public BizEmployee selectExchangeOrderTimesById(Integer id){
		return dao.selectExchangeOrderTimesById(id);
	};
	

	@Transactional
	public void updateExchangeOrderTimes(BizEmployee bizEmployee){
		dao.updateExchangeOrderTimes(bizEmployee);
	};

	@Transactional
	public void saveEmployee(BizEmployee emp){
			super.save(emp);
	}
	

	@Transactional
	public void updateEmployee(BizEmployee emp){
		dao.updateEmployee(emp);
	}
	
}