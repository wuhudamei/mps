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

		return dataAuthorityDao.findTransactionData(authorityId);
	}
	public List<DataAuthority> findDataPermissionOptions(String ids) {
		return dataAuthorityDao.findDataPermissionOptions(ids);
	}
	public List<String> findSysDataAuthRuleOptionRole(String roleId, String transactionId) {

		return dataAuthorityDao.findSysDataAuthRuleOptionRole(roleId,transactionId);
	}
	
	

	public String findModifiedTime(String authorityId) {

		
		return dataAuthorityDao.findModifiedTime(authorityId);
	}
	public Integer findOptionsByRoleId(String roleId, String ids) {

		return dataAuthorityDao.findOptionsByRoleId(roleId,ids);
	}
	public void insertSysDataAuthRuleOptionRole(DataAuthority da) {

		dataAuthorityDao.insertSysDataAuthRuleOptionRole(da);
	}
	public void updateSysDataAuthRuleOptionRole(DataAuthority da) {
		dataAuthorityDao.updateSysDataAuthRuleOptionRole(da);
		
	}
	public List<BizEmpStore> findStoreList(String da) {
		return dataAuthorityDao.findStoreList(da);
		
	}

	public List<String> orderdPhones(String code){
		DataAuthorityEntityConfirm data = new DataAuthorityEntityConfirm();
		User user = UserUtils.getUser();

		List<Role> roleList = user.getRoleList();
		
		data.setCode(code);
		data.setRoleList(roleList);

		String orderCode = dataAuthorityDao.findRuleOption(data);

		if(orderCode == null){
			return null;
		}
		List<String> findUserPhone = findUserPhone(orderCode);
		return findUserPhone;
		
	}

	

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

			if(split.length == 4){

				List<String> phones = dataAuthorityDao.findHeadmanPhone(id);
				return phones;
			}else{


				parentIds = parentIds+"%";

				List<String> phones = dataAuthorityDao.findInspectorGeneral(parentIds);
				return phones;
			}
		}else if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_ALL_DATE)){
			return null;
		}
		return null;
	}
	
	

	public List<String> findAuthorityCode(String code){
		DataAuthorityEntityConfirm data = new DataAuthorityEntityConfirm();
		User user = UserUtils.getUser();

		List<Role> roleList = user.getRoleList();
		
		data.setCode(code);
		data.setRoleList(roleList);

		String orderCode = dataAuthorityDao.findRuleOption(data);

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

			String id = office.getId();

			list = dataAuthorityDao.findInstitutionId(id);
			return list;
			
		}else if(optionId.equals(DataAuthorityConstantUtils.Auth_Rule_Option_ALL_DATE)){
			return null;
		}
		return null;
		
	}
	
	
	
	public void deleteSysDataAuthRuleOptionRole(String roleId, String string) {

		dataAuthorityDao.deleteSysDataAuthRuleOptionRole(roleId,string);
		
	}
	public List<DataAuthority> findTransaction() {

		return dataAuthorityDao.findTransaction();
	}
	
}
