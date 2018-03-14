
package cn.damei.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizTaskPackageTemplatDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;


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













    public static List<DropModel> getTaskList() {
    	BizTaskPackageTemplat param = new BizTaskPackageTemplat();
    	param.setStatus("1");
        List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findList(param);
        List<DropModel> taskList = new ArrayList<DropModel>();
        for (BizTaskPackageTemplat task : list) {
            taskList.add(new DropModel(task.getTemplatName(), task.getId()));
        }
        return taskList;
    }

    public static List<DropModel> getTaskListByNowStoreId() {
    	BizTaskPackageTemplat param = new BizTaskPackageTemplat();

    	param.setStatus("1");
        List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findList(param);
        List<DropModel> taskList = new ArrayList<DropModel>();
        for (BizTaskPackageTemplat task : list) {
            taskList.add(new DropModel(task.getTemplatName(), task.getId()));
        }
        return taskList;
    }










}
