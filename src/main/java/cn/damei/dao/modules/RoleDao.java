
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Role;


@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	public Role getByName(Role role);
	
	public void insertId(Role role);
	
	public Role getByEnname(Role role);


	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	

	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

}
