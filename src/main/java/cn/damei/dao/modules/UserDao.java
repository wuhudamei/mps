
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.SysRoleStoreRel;
import cn.damei.entity.modules.User;


@MyBatisDao
public interface UserDao extends CrudDao<User> {
	

	public User getByLoginName(User user);


	public List<User> findUserByOfficeId(User user);
	

	public long findAllCount(User user);
	

	public int updatePasswordById(User user);
	

	public int updateLoginInfo(User user);


	public int deleteUserRole(User user);
	

	public int insertUserRole(User user);
	

	public int updateUserInfo(User user);

	public BizEmployee getEmpInfoByUserEmpId(Integer empId);

	public User findUserByEmpId(Integer empId);

	


	public void saveRoleStore(SysRoleStoreRel srs);

	public void deleteSysRoleStoreRel(String id);

	public List<String> findStoreId(String id);

public List<BizEmpStore> findStoreList(List<String> roleIdList);

public BizEmpStore findAffiliationByRoleId(String id);

public void insertUser(User user);

public void empDelete(User user);

public String findStoreIdByOfficeId(String id);

}
