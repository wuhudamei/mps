/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizTaskPackageTemplatDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;

/**
 * 门店工具类
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
public class TaskPackageUtils {

    private static BizTaskPackageTemplatDao bizTaskPackageTemplatDao = SpringContextHolder.getBean(BizTaskPackageTemplatDao.class);
    public static final String CACHE_TackPackage_MAP = "taskPackageMap";

    public static String getTaskLabel(String value, String defaultValue) {
        if (StringUtils.isNotBlank(value)) {
            for (DropModel dict : getTaskList()) {
                if (value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static List<DropModel> getTaskListByStoreId(String value, String defaultValue) {
        List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findTaskPackageList(value);
        List<DropModel> taskList = new ArrayList<DropModel>();
        for (BizTaskPackageTemplat task : list) {
            taskList.add(new DropModel(task.getTemplatName(), task.getId()));
        }
        return taskList;
    }
    //
    // public static String getStoreValue(String label, String type, String
    // defaultLabel){
    // if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
    // for (BizEmpStore dict : getStoreList()){
    // if (type.equals(dict.getId()) && label.equals(dict.getDescription())){
    // return dict.getDescription();
    // }
    // }
    // }
    // return defaultLabel;
    // }

    public static List<DropModel> getTaskList() {
    	BizTaskPackageTemplat param = new BizTaskPackageTemplat();
    	param.setStatus("1");
        List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findList(param);
        List<DropModel> taskList = new ArrayList<DropModel>();// StoreDao.findTaskList();
        for (BizTaskPackageTemplat task : list) {
            taskList.add(new DropModel(task.getTemplatName(), task.getId()));
        }
        return taskList;
    }

    public static List<DropModel> getTaskListByNowStoreId() {
    	BizTaskPackageTemplat param = new BizTaskPackageTemplat();
//    	param.setStoreId(UserUtils.getUser().getStoreId());
    	param.setStatus("1");
        List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findList(param);
        List<DropModel> taskList = new ArrayList<DropModel>();// StoreDao.findTaskList();
        for (BizTaskPackageTemplat task : list) {
            taskList.add(new DropModel(task.getTemplatName(), task.getId()));
        }
        return taskList;
    }

    // /**
    // * 返回字典列表（JSON）
    // * @param type
    // * @return
    // */
    // public static String getStoreListJson(String type){
    // return JsonMapper.toJsonString(getStoreList(type));
    // }

}
