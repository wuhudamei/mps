/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.common.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmpStore;

/**
 * 门店工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
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
//	
//	public static String getStoreValue(String label, String type, String defaultLabel){
//		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
//			for (BizEmpStore dict : getStoreList()){
//				if (type.equals(dict.getId()) && label.equals(dict.getDescription())){
//					return dict.getDescription();
//				}
//			}
//		}
//		return defaultLabel;
//	}
	
	public static List<BizEmpStore> getStoreList(){
//		@SuppressWarnings("unchecked")
//		Map<String, List<BizEmpStore>> dictMap = (Map<String, List<BizEmpStore>>)CacheUtils.get(CACHE_STORE_MAP);
		List<BizEmpStore> storeList=StoreDao.findStoreList();
		//		if (dictMap==null){
//			dictMap = Maps.newHashMap();
//			for (BizEmpStore store : StoreDao.findStoreList()){
//				List<BizEmpStore> dictList = dictMap.get(store.getId());
//				if (dictList != null){
//					dictList.add(store);
//				}else{
//					dictMap.put(store.getId(), Lists.newArrayList(store));
//				}
//			}
//			CacheUtils.put(CACHE_STORE_MAP, dictMap);
//		}
//		List<BizEmpStore> dictList = dictMap.get();
//		if (dictList == null){
//			dictList = Lists.newArrayList();
//		}
		return storeList;
	}
	
	/**
	 * 获取任务包类型列表
	 * @return
	 */
	public static List<BizEmpStore> getTaskPackageList(){
		List<BizEmpStore> storeList=StoreDao.findStoreList();
		return storeList;
	}
	
	/**
	 * 获取任务包类型标签
	 * @param value
	 * @param defaultValue
	 * @return
	 */
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
	
//	/**
//	 * 返回字典列表（JSON）
//	 * @param type
//	 * @return
//	 */
//	public static String getStoreListJson(String type){
//		return JsonMapper.toJsonString(getStoreList(type));
//	}
	
}
