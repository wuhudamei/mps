
package cn.damei.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.ConstantUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.utils.CacheUtils;
import cn.damei.common.utils.SpringContextHolder;
import cn.damei.dao.modules.DictDao;
import cn.damei.entity.modules.Dict;


public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	public static List<Dict> getTaskPackageStatusList(String type){

		List<Dict> dictList = dictDao.getTaskPackageStatusList(type);
		return dictList;
	}
	
	public static List<Dict> getInstallProblemStatusList(String type){
		List<Dict> dictList = dictDao.getInstallProblemStatusList(type);
		return dictList;
	}
	
	public static List<Dict> getDictListByType(String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<Dict> dictList = dictDao.getDictListByType(map);
		return dictList;
	}

	public static String getOrderStatus(String value){
		List<Dict> list = getDictListByType(ConstantUtils.ORDER_STATUS);
		if(CollectionUtils.isNotEmpty(list)){
			for(Dict dict:list){
				if(value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "";
	}

	public static String getSettlementStatus(String value){
		List<Dict> list = getDictListByType(ConstantUtils.SETTLEMENT_STATUS);
		if(CollectionUtils.isNotEmpty(list)){
			for(Dict dict:list){
				if(value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "";
	}

	public static String getOrderTaskpackagePaymentStatus(String value){
		List<Dict> list = getDictListByType(ConstantUtils.PAYMENT_STATUS);
		if(CollectionUtils.isNotEmpty(list)){
			for(Dict dict:list){
				if(value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "";
	}

	public static List<Dict> getPaymentSummaryList(String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<String> valueList = new ArrayList<String>();
		valueList.add(ConstantUtils.SUMMARY_STATUS_20);
		valueList.add(ConstantUtils.SUMMARY_STATUS_50);
		valueList.add(ConstantUtils.SUMMARY_STATUS_100);
		map.put("valueList", valueList);
		List<Dict> dictList = dictDao.getDictListByType(map);
		return dictList;
	}

	public static String getPaymentSummaryStatus(String value){
		List<Dict> list = getPaymentSummaryList(ConstantUtils.SUMMARY_STATUS);
		if(CollectionUtils.isNotEmpty(list)){
			for(Dict dict:list){
				if(value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "";
	}

	public static List<Dict> getSummaryList(String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<String> valueList = new ArrayList<String>();
		valueList.add(ConstantUtils.SUMMARY_STATUS_10);
		valueList.add(ConstantUtils.SUMMARY_STATUS_20);
		valueList.add(ConstantUtils.SUMMARY_STATUS_50);
		valueList.add(ConstantUtils.SUMMARY_STATUS_100);
		map.put("valueList", valueList);
		List<Dict> dictList = dictDao.getDictListByType(map);
		return dictList;
	}

	public static String getSummaryStatus(String value){
		List<Dict> list = getSummaryList(ConstantUtils.SUMMARY_STATUS);
		if(CollectionUtils.isNotEmpty(list)){
			for(Dict dict:list){
				if(value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "";
	}

	public static List<Dict> getPaymentList(String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<String> valueList = new ArrayList<String>();
		valueList.add(ConstantUtils.PAYMENT_STATUS_10);
		valueList.add(ConstantUtils.PAYMENT_STATUS_15);
		valueList.add(ConstantUtils.PAYMENT_STATUS_20);
		valueList.add(ConstantUtils.PAYMENT_STATUS_30);
		valueList.add(ConstantUtils.PAYMENT_STATUS_40);
		valueList.add(ConstantUtils.PAYMENT_STATUS_100);
		map.put("valueList", valueList);
		List<Dict> dictList = dictDao.getDictListByType(map);
		return dictList;
	}

	public static List<Dict> getEmpTypeList(String type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		List<String> valueList = new ArrayList<String>();
		valueList.add(ConstantUtils.EMP_TYPE_1);
		valueList.add(ConstantUtils.EMP_TYPE_2);
		valueList.add(ConstantUtils.EMP_TYPE_3);
		map.put("valueList", valueList);
		List<Dict> dictList = dictDao.getDictListByType(map);
		return dictList;
	}

	public static String getPaymentStatus(String value){
		List<Dict> list = getPaymentList(ConstantUtils.PAYMENT_STATUS);
		if(CollectionUtils.isNotEmpty(list)){
			for(Dict dict:list){
				if(value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return "";
	}

	public static Boolean checkIsExits(String oldStr,String newStr){
		if(StringUtils.isNoneBlank(oldStr)){
			String[] s = oldStr.split(",");
			for(String str:s){
				if(str.equals(newStr)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<Dict> getOrderTaskPackageStatusList(String type){

		List<Dict> dictList = dictDao.getOrderTaskPackageStatusList(type);
		return dictList;
	}
	
	public static List<Dict> getSummaryStatusList(String type){

		List<Dict> dictList = dictDao.getSummaryStatusList(type);
		return dictList;
	}
	
	public static List<Dict> getSummaryCheckedStatusList(String type){

		List<Dict> dictList = dictDao.getSummaryCheckedStatusList(type);
		return dictList;
	}
	

	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}
