
package cn.damei.common.utils.excel.fieldtype;

import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.Office;
import cn.damei.common.utils.UserUtils;


public class OfficeType {


	public static Object getValue(String val) {
		for (Office e : UserUtils.getOfficeList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}


	public static String setValue(Object val) {
		if (val != null && ((Office)val).getName() != null){
			return ((Office)val).getName();
		}
		return "";
	}
}
