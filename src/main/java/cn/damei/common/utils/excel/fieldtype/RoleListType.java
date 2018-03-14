
package cn.damei.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.Collections3;
import cn.damei.common.utils.SpringContextHolder;
import cn.damei.entity.modules.Role;
import cn.damei.service.modules.SystemService;


public class RoleListType {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	

	public static Object getValue(String val) {
		List<Role> roleList = Lists.newArrayList();
		List<Role> allRoleList = systemService.findAllRole();
		for (String s : StringUtils.split(val, ",")){
			for (Role e : allRoleList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}


	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}
