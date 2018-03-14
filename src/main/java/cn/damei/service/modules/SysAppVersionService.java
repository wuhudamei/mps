/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.SysAppVersion;
import cn.damei.dao.modules.SysAppVersionDao;

/**
 * 手机app版本Service
 * @author qww
 * @version 2016-12-27
 */
@Service
@Transactional(readOnly = true)
public class SysAppVersionService extends CrudService2<SysAppVersionDao, SysAppVersion> {

	public SysAppVersion get(Integer id) {
		return super.get(id);
	}
	
	public List<SysAppVersion> findList(SysAppVersion sysAppVersion) {
		return super.findList(sysAppVersion);
	}
	
	public Page<SysAppVersion> findPage(Page<SysAppVersion> page, SysAppVersion sysAppVersion) {
		sysAppVersion.setType(ConstantUtils.APP_TYPE);
		return super.findPage(page, sysAppVersion);
	}
	
	@Transactional(readOnly = false)
	public void save(SysAppVersion sysAppVersion) {
		User user = UserUtils.getUser();
		sysAppVersion.setUploadEmployeeId(Integer.parseInt(user.getId()));
		sysAppVersion.setUploadDatetime(new Date());
		super.save(sysAppVersion);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysAppVersion sysAppVersion) {
		super.delete(sysAppVersion);
	}

	/**
	 * 查询最大版本号
	 * @param appType
	 * @return
	 */
	public String queryMaxVersion(String appType) {
		return dao.queryMaxVersion(appType);
	}

	/**
	 * 查询最大版本号
	 * @param sysAppVersion
	 * @return
	 */
	public String checkVersionIsExits(SysAppVersion sysAppVersion) {
		String flag = "0";
		if(sysAppVersion.getId() != null){
			SysAppVersion version = super.get(sysAppVersion.getId());
			if(!version.getVersion().equals(sysAppVersion.getVersion())){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("appType", sysAppVersion.getAppType());
				map.put("version", sysAppVersion.getVersion());
				int count = dao.queryCountVersion(map);
				if(count > 0){
					flag = "0";
					return flag;
				}
				flag = "1";
				return flag;
			}
			flag = "1";
			return flag;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appType", sysAppVersion.getAppType());
			map.put("version", sysAppVersion.getVersion());
			int count = dao.queryCountVersion(map);
			if(count > 0){
				flag = "0";
				return flag;
			}
			flag = "1";
			return flag;
		}
	}
	
	public SysAppVersion queryNewestVersion(String appType){
		return dao.queryNewestVersion(appType);
	}
}