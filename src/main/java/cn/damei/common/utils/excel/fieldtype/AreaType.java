
package cn.damei.common.utils.excel.fieldtype;

import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.Area;
import cn.damei.common.utils.UserUtils;


public class AreaType {


	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}


	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
