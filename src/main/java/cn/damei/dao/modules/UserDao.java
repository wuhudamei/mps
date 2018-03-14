/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.SysRoleStoreRel;
import cn.damei.entity.modules.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	/**
	 * 根据登陆者员工empId外键查询员工工程模式
	 * @return
	 */
	public BizEmployee getEmpInfoByUserEmpId(Integer empId);

	public User findUserByEmpId(Integer empId);

	
/**
 * 保存角色对应的门店
 */

	public void saveRoleStore(SysRoleStoreRel srs);
/**
 * 删除角色对应的门店
 */
	public void deleteSysRoleStoreRel(String id);
/**
 * 查询所有角色的门店
 */
	public List<String> findStoreId(String id);

public List<BizEmpStore> findStoreList(List<String> roleIdList);
/**
 * 查询角色的机构
 * @param id
 * @return
 */
public BizEmpStore findAffiliationByRoleId(String id);
/**
 * 插入成功后返回主键id
 * @param user
 * @return
 */
public void insertUser(User user);
/**
 * 删除用户
 * @param user
 */
public void empDelete(User user);

public String findStoreIdByOfficeId(String id);

}
