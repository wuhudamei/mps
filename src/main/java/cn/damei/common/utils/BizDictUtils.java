
package cn.damei.common.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.damei.dao.modules.BizAuxiliaryMaterialsDao;
import cn.damei.entity.modules.BizAuxiliaryMaterials;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.dao.modules.BizMaterialCategoryDao;
import cn.damei.entity.modules.BizMaterialCategory;
import cn.damei.dao.modules.BizProcedureDao;
import cn.damei.entity.modules.BizProcedure;
import cn.damei.dao.modules.ScoreOrderDao;
import cn.damei.dao.modules.BizStarDao;
import cn.damei.entity.modules.BizStar;
import cn.damei.dao.modules.BizSupplierDao;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.dao.modules.OfficeDao;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.User;
import cn.damei.dao.modules.BizTaskPackageTemplatDao;
import cn.damei.dao.modules.BizTaskPackageTypeDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.entity.modules.BizTaskPackageType;


public class BizDictUtils {

	private static BizEmployeeDao storeDao = SpringContextHolder.getBean(BizEmployeeDao.class);
	private static BizTaskPackageTypeDao bizTaskPackageTypeDao = SpringContextHolder
			.getBean(BizTaskPackageTypeDao.class);
	private static BizProcedureDao bizProcedureDao = SpringContextHolder.getBean(BizProcedureDao.class);
	private static BizStarDao StarDao = SpringContextHolder.getBean(BizStarDao.class);
	private static BizTaskPackageTemplatDao bizTaskPackageTemplatDao = SpringContextHolder
			.getBean(BizTaskPackageTemplatDao.class);
	private static BizAuxiliaryMaterialsDao bizAuxiliaryMaterialsDao = SpringContextHolder
			.getBean(BizAuxiliaryMaterialsDao.class);

	private static BizMaterialCategoryDao bizMaterialCategoryDao = SpringContextHolder
			.getBean(BizMaterialCategoryDao.class);
	private static BizSupplierDao bizSupplierDao = SpringContextHolder.getBean(BizSupplierDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	private static ScoreOrderDao scoreOrderDao = SpringContextHolder.getBean(ScoreOrderDao.class);

	public static final String CACHE_STORE_MAP = "storeMap";

	public static String getStoreLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			List<BizEmpStore> data = getStoreList();
			for (BizEmpStore dict : data) {
				if (value.equals(dict.getValue())) {
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	public static List<BizEmpStore> getContentType() {
		List<Map<String,Object>> a =scoreOrderDao.getScoreContent();
		List<BizEmpStore> storeList = new ArrayList<BizEmpStore>();
		for (int i = 0; i < a.size(); i++) {
			BizEmpStore bizEmpStore = new BizEmpStore();
			bizEmpStore.setLabel((String)a.get(i).get("typeName"));
			bizEmpStore.setValue((String)a.get(i).get("typeCode"));
			storeList.add(bizEmpStore);
		}
		return storeList;
	}
	public static List<BizEmpStore> getComplainType() {
		List<Map<String,Object>> a =scoreOrderDao.getScoreComplain();
		List<BizEmpStore> storeList = new ArrayList<BizEmpStore>();
		for (int i = 0; i < a.size(); i++) {
			BizEmpStore bizEmpStore = new BizEmpStore();
			bizEmpStore.setLabel((String)a.get(i).get("label"));
			bizEmpStore.setValue((String)a.get(i).get("value"));
			storeList.add(bizEmpStore);
		}
		return storeList;
	}
	public static List<BizEmpStore> getPostionList() {
		List<Map<String,Object>> a =scoreOrderDao.selectPositionType();
		List<BizEmpStore> storeList = new ArrayList<BizEmpStore>();
		for (int i = 0; i < a.size(); i++) {
			BizEmpStore bizEmpStore = new BizEmpStore();
			bizEmpStore.setLabel((String)a.get(i).get("employeePost"));
			bizEmpStore.setValue((String)a.get(i).get("employeePost"));
			storeList.add(bizEmpStore);
		}
		return storeList;
	}













	public static List<BizEmpStore> getStoreList() {
		List<BizEmpStore> storeList = new ArrayList<BizEmpStore>();
        User user = UserUtils.getUser();
        String empId = user.getEmpId();
        String storeId  = user.getOffice().getId();
        if(empId == null || "1".equals(storeId)){
            BizEmpStore store = new BizEmpStore();
            store.setValue("");
            store.setLabel("");
            storeList.add(store);
            storeList.addAll(storeDao.findStoreList());
        }else{
            Office officeLabel = storeDao.findStoreLabel(empId);
            BizEmpStore store = new BizEmpStore();
            store.setValue(officeLabel.getId());
            store.setLabel(officeLabel.getName());
            storeList.add(store);
        }
		return storeList;
	}


	public static List<BizTaskPackageType> getTaskPackageTypeList() {
		BizTaskPackageType param = new BizTaskPackageType();
		param.setStatus("1");
		List<BizTaskPackageType> list = bizTaskPackageTypeDao.findList(param);
		return list;
	}


	public static String getTaskPackageTypeLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			for (BizTaskPackageType dict : getTaskPackageTypeList()) {
				if (value.equals(dict.getId())) {
					return dict.getName();
				}
			}
		}
		return defaultValue;
	}


	public static String getTaskPackageTemplateLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {



			BizTaskPackageTemplat param = new BizTaskPackageTemplat();
			param.setStoreId(UserUtils.getUser().getStoreId());
			List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findList(param);
			for (BizTaskPackageTemplat dict : list) {
				if (value.equals(dict.getId())) {
					return dict.getTemplatName();
				}
			}
		}
		return defaultValue;
	}


	public static List<BizTaskPackageTemplat> getTaskPackageTemplate() {
		return bizTaskPackageTemplatDao.queryTaskpackageTemplat();
	}


	public static List<BizProcedure> getProcedureList() {
		BizProcedure param = new BizProcedure();
		List<BizProcedure> list = bizProcedureDao.findList(param);
		return list;
	}


	public static String getProcedureLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			for (BizProcedure dict : getProcedureList()) {
				if (value.equals(dict.getProcedureNo())) {
					return dict.getProcedureName();
				}
			}
		}
		return defaultValue;
	}


	public static List<BizEmpStore> getStoreListAdd() {

		System.out.println("*************getStoreListAdd begin*************");
		List<BizEmpStore> store = new ArrayList<BizEmpStore>();
		if (UserUtils.getUser().getStoreId() == null) {

			List<BizEmpStore> storeList = storeDao.findStoreList();
			for (BizEmpStore bizEmpStore : storeList) {

				List<BizStar> bizStar = StarDao.findStarByStoreId(bizEmpStore.getValue());

				if (bizStar != null && bizStar.size() > 1) {
				} else {
					store.add(bizEmpStore);
				}
			}
		} else {

			Office office = officeDao.get(UserUtils.getUser().getStoreId());
			BizEmpStore store2 = new BizEmpStore();
			store2.setValue(office.getId());
			store2.setLabel(office.getLabel());
			store.add(store2);
		}
		System.out.println("*************getStoreListAdd finish*************");

		return store;
	}

	public static String getStoreLabelAdd(String value, String defaultValue) {
		System.out.println("*************getStoreLabelAdd begin*************");
		if (StringUtils.isNotBlank(value)) {
			for (BizEmpStore dict : getStoreListAdd()) {
				if (value.equals(dict.getValue())) {
					return dict.getLabel();
				}
			}
		}
		System.out.println("*************getStoreLabelAdd begin*************");
		return defaultValue;
	}


	public static List<BizAuxiliaryMaterials> getAuxiliaryMaterialsList() {
		BizAuxiliaryMaterials param = new BizAuxiliaryMaterials();

		param.setStatus("1");
		List<BizAuxiliaryMaterials> list = bizAuxiliaryMaterialsDao.findList(param);
		return list;
	}


	public static String getAuxiliaryMaterialsLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			for (BizAuxiliaryMaterials dict : getAuxiliaryMaterialsList()) {
				if (value.equals(dict.getAuxiliaryMaterialsNo())) {
					return dict.getAuxiliaryMaterialsName();
				}
			}
		}
		return defaultValue;
	}


	public static List<BizSupplier> getSupplierList() {
		BizSupplier param = new BizSupplier();
		param.setStatus("1");
		List<BizSupplier> list = bizSupplierDao.findList(param);
		return list;
	}


	public static String getSupplierLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			for (BizSupplier dict : getSupplierList()) {
				if (value.equals(dict.getId())) {
					return dict.getSupplierName();
				}
			}
		}
		return defaultValue;
	}


	public static List<BizMaterialCategory> getMaterialCategoryList() {
		BizMaterialCategory param = new BizMaterialCategory();
		param.setMaterialTypeId(ConstantUtils.AUXILIARY_MATERIALS_CATEGORY);
		param.setStatus("1");
		List<BizMaterialCategory> list = bizMaterialCategoryDao.findList(param);
		return list;
	}


	public static String getMaterialCategoryLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			for (BizMaterialCategory dict : getMaterialCategoryList()) {
				if (value.equals(dict.getId())) {
					return dict.getCategoryName();
				}
			}
		}
		return defaultValue;
	}


	public static List<BizMaterialCategory> getMainMaterialCategoryList() {
		BizMaterialCategory param = new BizMaterialCategory();
		param.setMaterialTypeId(ConstantUtils.MAIN_MATERIALS_CATEGORY);
		param.setStatus("1");
		List<BizMaterialCategory> list = bizMaterialCategoryDao.findList(param);
		return list;
	}


	public static String getMainMaterialCategoryLabel(String value, String defaultValue) {
		if (StringUtils.isNotBlank(value)) {
			for (BizMaterialCategory dict : getMainMaterialCategoryList()) {
				if (value.equals(dict.getId())) {
					return dict.getCategoryName();
				}
			}
		}
		return defaultValue;
	}


	public static List<Dict> getqualityGuaranteeRateList() {
		List<Dict> list = new LinkedList<Dict>();
		Dict dict = null;
		for (int i = 1; i <= 100; i++) {
			dict = new Dict(i + "", i + "");
			list.add(dict);
		}
		return list;
	}

}
