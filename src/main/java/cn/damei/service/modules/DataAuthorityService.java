package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.entity.modules.DataAuthorityEntityConfirm;
import cn.damei.dao.modules.DataAuthorityDao;
import cn.damei.entity.modules.DataAuthority;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.Role;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
@Service
@Transactional(readOnly = false)
public class DataAuthorityService extends CrudService<DataAuthorityDao, DataAuthority> {
@Autowired
private DataAuthorityDao dataAuthorityDao;
@Autowired
private BizProjectChangeBillService bizProjectChangeBillService;
	public List<Integer> findTransactionDataList() {
		List<Integer> list = dataAuthorityDao.findTransactionDataList();
		
		return list;
	}
	public List<DataAuthority> findTransactionData(String authorityId) {
		// TODO Auto-generated method stub
		return dataAuthorityDao.findTransactionData(authorityId);
	}
	public List<DataAuthority> findDataPermissionOptions(String ids) {
		return dataAuthorityDao.findDataPermissionOptions(ids);
	}
	public List<String> findSysDataAuthRuleOptionRole(String roleId, String transactionId) {
		// TODO Auto-generated method stub
		return dataAuthorityDao.findSysDataAuthRuleOptionRole(roleId,transactionId);
	}
	
	

	public String findModifiedTime(String authorityId) {
		// TODO Auto-generated method stub
		
		return dataAuthorityDao.findModifiedTime(authorityId);
	}
	public Integer findOptionsByRoleId(String roleId, String ids) {
		// TODO Auto-generated method stub
		return dataAuthorityDao.findOptionsByRoleId(roleId,ids);
	}
	public void insertSysDataAuthRuleOptionRole(DataAuthority da) {
		// TODO Auto-generated method stub
		dataAuthorityDao.insertSysDataAuthRuleOptionRole(da);
	}
	public void updateSysDataAuthRuleOptionRole(DataAuthority da) {
		dataAuthorityDao.updateSysDataAuthRuleOptionRole(da);
		
	}
	public List<BizEmpStore> findStoreList(String da) {
		return dataAuthorityDao.findStoreList(da);
		
	}
	/**
	 * 订单
	 * @return
	 */
	public List<String> orderdPhones(String code){
		DataAuthorityEntityConfirm data = new DataAuthorityEntityConfirm();
		User user = UserUtils.getUser();
		//获取角色列表
		List<Role> roleList = user.getRoleList();
		
		data.setCode(code);
		data.setRoleList(roleList);
		//查询权限编码
		String orderCode = dataAuthorityDao.findRuleOption(data);
		//根据权限查询手机号
		if(orderCode == null){
			return null;
		}
		List<String> findUserPhone = findUserPhone(orderCode);
		return findUserPhone;
		
	}

	
	//查询手机号
	public List<String> findUserPhone(String optionId){
		User user = UserUtils.getUser();
		List<String> list = new ArrayList<>();
		if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_MYSELF)){
			String phone = user.getPhone();
			list.add(phone);
			return list;
		}else if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_CUR_AND_SUB_OFFICE)){
			Office office = user.getOffice();
			String parentIds = office.getParentIds();
			String id = office.getId();
			String[] split = parentIds.split(",");
			//如果是组长
			if(split.length == 4){
				/*data.setParentId(null);
				data.setOfficeId(id);*/
				List<String> phones = dataAuthorityDao.findHeadmanPhone(id);
				return phones;
			}else{
				//总监
				/*data.setOfficeId(null);*/
				parentIds = parentIds+"%";
				/*data.setParentId(parentIds);*/
				List<String> phones = dataAuthorityDao.findInspectorGeneral(parentIds);
				return phones;
			}
		}else if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_ALL_DATE)){
			return null;
		}
		return null;
	}
	
	
	/**
	 * 审计部权限控制
	 * @param roleId
	 * @param string
	 */
	public List<String> findAuthorityCode(String code){
		DataAuthorityEntityConfirm data = new DataAuthorityEntityConfirm();
		User user = UserUtils.getUser();
		//获取角色列表
		List<Role> roleList = user.getRoleList();
		
		data.setCode(code);
		data.setRoleList(roleList);
		//查询权限编码
		String orderCode = dataAuthorityDao.findRuleOption(data);
		//根据权限查询手机号
		if(orderCode == null){
			return null;
		}
		List<String> ids = findProjectChangeBillId(orderCode);
		return ids;
	}
	
	public List<String> findProjectChangeBillId(String optionId){
		User user = UserUtils.getUser();
		List<String> list = new ArrayList<>();
		if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_MYSELF)){
			String id = user.getEmpId();
			list.add(id);
			return list;
		}else if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_CUR_AND_SUB_OFFICE)){
			Office office = user.getOffice();
			/*String parentIds = office.getParentIds();*/
			String id = office.getId();
			//查询本机构下所有的人
			list = dataAuthorityDao.findInstitutionId(id);
			return list;
			
		}else if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_ALL_DATE)){
			return null;
		}
		return null;
		
	}
	
	
	
	public void deleteSysDataAuthRuleOptionRole(String roleId, String string) {
		// TODO Auto-generated method stub
		dataAuthorityDao.deleteSysDataAuthRuleOptionRole(roleId,string);
		
	}
	public List<DataAuthority> findTransaction() {
		// TODO Auto-generated method stub
		return dataAuthorityDao.findTransaction();
	}
	
}
