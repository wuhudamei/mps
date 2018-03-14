package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DataAuthorityEntityConfirm;
import cn.damei.entity.modules.DataAuthority;
import cn.damei.entity.modules.BizEmpStore;

@MyBatisDao
public interface DataAuthorityDao extends CrudDao<DataAuthority>{

	List<Integer> findTransactionDataList();

	List<DataAuthority> findTransactionData(String authorityId);

	List<DataAuthority> findDataPermissionOptions(String ids);

	List<String> findSysDataAuthRuleOptionRole(String roleId, String transactionId);

	


	String findModifiedTime(String authorityId);

	Integer findOptionsByRoleId(String roleId, String ids);

	void insertSysDataAuthRuleOptionRole(DataAuthority da);

	void updateSysDataAuthRuleOptionRole(DataAuthority da);

	List<BizEmpStore> findStoreList(String id);
	//组长
	List<String> findHeadmanPhone(String id);
	//总监
	List<String> findInspectorGeneral(String parentIds);

	void deleteSysDataAuthRuleOptionRole(String roleId, String string);

	String findRuleOption(DataAuthorityEntityConfirm data);

	List<DataAuthority> findTransaction();

	List<String> findInstitutionId(String id);

}
