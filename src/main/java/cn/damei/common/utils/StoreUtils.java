
package cn.damei.common.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmpStore;


public class StoreUtils {
	
	private static BizEmployeeDao StoreDao = SpringContextHolder.getBean(BizEmployeeDao.class);

	public static final String CACHE_STORE_MAP = "storeMap";
	
	public static String getStoreLabel(String value, String defaultValue){
		if (StringUtils.isNotBlank(value)){
			for (BizEmpStore dict : getStoreList()){
				if (value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}











	
	public static List<BizEmpStore> getStoreList(){


		List<BizEmpStore> storeList=StoreDao.findStoreList();
















		return storeList;
	}
	

	public static List<BizEmpStore> getTaskPackageList(){
		List<BizEmpStore> storeList=StoreDao.findStoreList();
		return storeList;
	}
	

	public static String getTaskPackageLabel(String value, String defaultValue){
		if (StringUtils.isNotBlank(value)){
			for (BizEmpStore dict : getTaskPackageList()){
				if (value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	








	
}
