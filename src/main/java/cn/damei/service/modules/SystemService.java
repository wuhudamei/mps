
package cn.damei.service.modules;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.security.Digests;
import cn.damei.common.security.shiro.session.SessionDAO;
import cn.damei.common.service.BaseService;
import cn.damei.common.service.ServiceException;
import cn.damei.common.utils.CacheUtils;
import cn.damei.common.utils.Encodes;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.Servlets;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.dao.modules.MenuDao;
import cn.damei.dao.modules.RoleDao;
import cn.damei.dao.modules.UserDao;
import cn.damei.entity.modules.Menu;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.Role;
import cn.damei.entity.modules.SysRoleStoreRel;
import cn.damei.entity.modules.User;
import cn.damei.common.SystemAuthorizingRealm;
import cn.damei.common.utils.LogUtils;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private SessionDAO sessionDao;
	@Autowired
	private SystemAuthorizingRealm systemRealm;
	@Autowired
	private BizEmployeeService bes;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	@Autowired
	private BizEmployeeService bizEmployeeService;
	@Autowired
    private SysSequenceService sysSequenceService;
	
	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	@Autowired
	private IdentityService identityService;


	

	public User getUser(String id) {
		return UserUtils.get(id);
	}


	public User getUserByLoginName(String loginName) {
		return UserUtils.getByLoginName(loginName);
	}
	
	public Page<User> findUser(Page<User> page, User user) {

		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));

		user.setPage(page);

		page.setList(userDao.findList(user));
		return page;
	}
	

	public List<User> findUser(User user){

		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		List<User> list = userDao.findList(user);
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<User> findUserByOfficeId(String officeId) {
		List<User> list = (List<User>)CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
		if (list == null){
			User user = new User();
			user.setOffice(new Office(officeId));
			list = userDao.findUserByOfficeId(user);
			CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
		}
		return list;
	}
	
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		String str = userDao.findStoreIdByOfficeId(user.getOffice().getId());
		String[] split = str.split(",");
		if (StringUtils.isBlank(user.getId())){
			
			user.preUserInsert();
			userDao.insertUser(user);
			

			BizEmployee be = new BizEmployee();
			be.setNo(sysSequenceService.getSequence("YG"));
			be.setRealname(user.getName());
			be.setLoginname(user.getLoginName());
			be.setPhone(user.getPhone());
			if(split.length > 2){
				be.setStoreid(split[2]);
			}else{
				be.setStoreid(user.getOffice().getId());
			}
			be.setIsNewRecord(true);

			be.setSysuserid(user.getId());
			be.preInsert();

			bes.saveEmployee(be);

			user.setEmpId(be.getId());
			userDao.update(user);

			userDao.insertUserRole(user);
		}else{

			User oldUser = userDao.get(user.getId());
			if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null){
				CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
			}

			user.preUpdate();
			userDao.update(user);
			
			
			

			String empId = oldUser.getEmpId();
			BizEmployee be = new BizEmployee();
			be.setId(empId);
			be.setRealname(user.getName());
			be.setLoginname(user.getLoginName());
			be.setPhone(user.getPhone());
			if(split.length > 2){
				be.setStoreid(split[2]);
			}else{
				be.setStoreid(user.getOffice().getId());
			}
			be.preInsert();
			be.setId(empId);

			bes.updateEmployee(be);
		}
		if (StringUtils.isNotBlank(user.getId())){

			userDao.deleteUserRole(user);
			if (user.getRoleList() != null && user.getRoleList().size() > 0){
				userDao.insertUserRole(user);
			}else{
				throw new ServiceException(user.getLoginName() + "没有设置角色！");
			}

			saveActivitiUser(user);

			UserUtils.clearCache(user);


		}
	}
	
	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);

		UserUtils.clearCache(user);


	}
	
	@Transactional(readOnly = false)
	public void deleteUser(User user) {

		userDao.delete(user);

		BizEmployee bizEmployee = new BizEmployee();
		User oldUser = userDao.get(user.getId());
		bizEmployee.setId(oldUser.getEmpId());
		bizEmployeeService.delete(bizEmployee);

		deleteActivitiUser(user);

		UserUtils.clearCache(user);


	}
	
	@Transactional(readOnly = false)
	public void updatePasswordById(String id, String loginName, String newPassword) {
		User user = new User(id);
		user.setPassword(entryptPassword(newPassword));
		userDao.updatePasswordById(user);

		user.setLoginName(loginName);
		UserUtils.clearCache(user);


	}
	
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(User user) {

		user.setOldLoginIp(user.getLoginIp());
		user.setOldLoginDate(user.getLoginDate());

		user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		user.setLoginDate(new Date());
		userDao.updateLoginInfo(user);
	}
	

	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	

	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	

	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	

	
	public Role getRole(String id) {
		return roleDao.get(id);
	}

	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setName(name);
		return roleDao.getByName(r);
	}
	
	public Role getRoleByEnname(String enname) {
		Role r = new Role();
		r.setEnname(enname);
		return roleDao.getByEnname(r);
	}
	
	public List<Role> findRole(Role role){
		return roleDao.findList(role);
	}
	
	public List<Role> findAllRole(){
		return UserUtils.getRoleList();
	}
	
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())){
			role.preInsert();
			roleDao.insertId(role);

			saveActivitiGroup(role);
		}else{
			role.preUpdate();
			roleDao.update(role);
		}

		roleDao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0){
			roleDao.insertRoleMenu(role);
		}

		roleDao.deleteRoleOffice(role);
		if (role.getOfficeList().size() > 0){
			roleDao.insertRoleOffice(role);
		}

		saveActivitiGroup(role);

		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);


	}

	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);

		deleteActivitiGroup(role);

		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);


	}
	
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		List<Role> roles = user.getRoleList();
		for (Role e : roles){
			if (e.getId().equals(role.getId())){
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null){
			return null;
		}
		List<String> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);
		saveUser(user);
		return user;
	}


	
	public Menu getMenu(String id) {
		return menuDao.get(id);
	}

	public List<Menu> findAllMenu(){
		return UserUtils.getMenuList();
	}
	
	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {
		

		menu.setParent(this.getMenu(menu.getParent().getId()));
		

		String oldParentIds = menu.getParentIds(); 
		

		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");


		if (StringUtils.isBlank(menu.getId())){
			menu.preInsert();
			menuDao.insert(menu);
		}else{
			menu.preUpdate();
			menuDao.update(menu);
		}
		

		Menu m = new Menu();
		m.setParentIds("%,"+menu.getId()+",%");
		List<Menu> list = menuDao.findByParentIdsLike(m);
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuDao.updateParentIds(e);
		}

		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);



		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void updateMenuSort(Menu menu) {
		menuDao.updateSort(menu);

		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);



		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void deleteMenu(Menu menu) {
		menuDao.delete(menu);

		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);



		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}
	

	public static boolean printKeyLoadMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+Global.getConfig("productName")+"\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}
	

	



	private static boolean isSynActivitiIndetity = true;
	public void afterPropertiesSet() throws Exception {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if (isSynActivitiIndetity){
			isSynActivitiIndetity = false;

			List<Group> groupList = identityService.createGroupQuery().list();
			if (groupList.size() == 0){
			 	Iterator<Role> roles = roleDao.findAllList(new Role()).iterator();
			 	while(roles.hasNext()) {
			 		Role role = roles.next();
			 		saveActivitiGroup(role);
			 	}
			}

			List<org.activiti.engine.identity.User> userList = identityService.createUserQuery().list();
			if (userList.size() == 0){
			 	Iterator<User> users = userDao.findAllList(new User()).iterator();
			 	while(users.hasNext()) {
			 		saveActivitiUser(users.next());
			 	}
			}
		}
	}
	
	private void saveActivitiGroup(Role role) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		String groupId = role.getEnname();
		

		if (StringUtils.isNotBlank(role.getOldEnname()) && !role.getOldEnname().equals(role.getEnname())){
			identityService.deleteGroup(role.getOldEnname());
		}
		
		Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
		if (group == null) {
			group = identityService.newGroup(groupId);
		}
		group.setName(role.getName());
		group.setType(role.getRoleType());
		identityService.saveGroup(group);
		

		List<org.activiti.engine.identity.User> activitiUserList = identityService.createUserQuery().memberOfGroup(groupId).list();
		for (org.activiti.engine.identity.User activitiUser : activitiUserList){
			identityService.deleteMembership(activitiUser.getId(), groupId);
		}


		List<User> userList = findUser(new User(new Role(role.getId())));
		for (User e : userList){
			String userId = e.getLoginName();

			org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
			if (activitiUser == null){
				activitiUser = identityService.newUser(userId);
				activitiUser.setFirstName(e.getName());
				activitiUser.setLastName(StringUtils.EMPTY);
				activitiUser.setEmail(e.getEmail());
				activitiUser.setPassword(StringUtils.EMPTY);
				identityService.saveUser(activitiUser);
			}
			identityService.createMembership(userId, groupId);
		}
	}

	public void deleteActivitiGroup(Role role) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(role!=null) {
			String groupId = role.getEnname();
			identityService.deleteGroup(groupId);
		}
	}

	private void saveActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		String userId = user.getLoginName();
		org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
		if (activitiUser == null) {
			activitiUser = identityService.newUser(userId);
		}
		activitiUser.setFirstName(user.getName());
		activitiUser.setLastName(StringUtils.EMPTY);
		activitiUser.setEmail(user.getEmail());
		activitiUser.setPassword(StringUtils.EMPTY);
		identityService.saveUser(activitiUser);
		

		List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
		for (Group group : activitiGroups) {
			identityService.deleteMembership(userId, group.getId());
		}

		for (Role role : user.getRoleList()) {
	 		String groupId = role.getEnname();

		 	Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
            if(group == null) {
	            group = identityService.newGroup(groupId);
	            group.setName(role.getName());
	            group.setType(role.getRoleType());
	            identityService.saveGroup(group);
            }
			identityService.createMembership(userId, role.getEnname());
		}
	}

	private void deleteActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(user!=null) {
			String userId = user.getLoginName();
			identityService.deleteUser(userId);
		}
	}
	


	public BizEmployee getEmpInfoByUserEmpId(Integer empId){
		
		return userDao.getEmpInfoByUserEmpId(empId);
	}


	@Transactional(readOnly = false)
	public void saveRoleStore(SysRoleStoreRel srs) {

		 userDao.saveRoleStore(srs);
	}
	@Transactional(readOnly = false)
	public void deleteSysRoleStoreRel(String id) {

		userDao.deleteSysRoleStoreRel(id);
	}

	public List<String> findStoreId(String id) {

		return userDao.findStoreId(id);
	}

	public List<BizEmpStore> findStoreList(List<String> roleIdList) {

		return userDao.findStoreList(roleIdList);
	}

	public BizEmpStore findAffiliationByRoleId(String id) {

		return userDao.findAffiliationByRoleId(id);
	}
}
