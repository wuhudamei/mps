/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.dao.modules.BizEngineeringDepartmentDao;
import cn.damei.entity.modules.Office;

/**
 * 员工工具类
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DropUtils {

    private static BizEmployeeDao employeeDao = SpringContextHolder.getBean(BizEmployeeDao.class);
    private static BizEngineeringDepartmentDao engineeringDepartmentDao = SpringContextHolder.getBean(BizEngineeringDepartmentDao.class);

    public static String getEmployeeLabel(String value, String defaultValue) {
        if (StringUtils.isNotBlank(value)) {
            for (DropModel emp : getEmployeeList("")) {
                if (value.equals(emp.getValue())) {
                    return emp.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getElacLabel(String value, String defaultValue) {
    	if (StringUtils.isNotBlank(value)) {
            for (DropModel dict : getElacList()) {
                if (value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
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

    public static List<DropModel> getEmployeeList(String value) {
        BizEmployee employee = new BizEmployee();
        if (value != null && !value.equals("")) {
            employee.setEmpType(Integer.valueOf(value));
        }
        List<DropModel> employeeList = employeeDao.findEmployeeList(employee);
        return employeeList;
    }
    
    /**
     * 根据登录员工的工程部,返回不同的工程部list
     * 如果登录员工有多个工程部或没有工程部,  返回list+空的开头 (空的开头是默认值)
     * 如果登录员工有一个工程部, 那么默认该工程部label为第一个
     *
     * @return
     */
    public static List<DropModel> getEngineListWithUserConditions() {
    	 List<DropModel> elactricationList  = new  ArrayList<DropModel>();
    	DropModel model = new DropModel();
    	model.setValue("");
    	model.setLabel("");
    	String loginUserEmpId = UserUtils.getUser().getEmpId();
    	if(null!=loginUserEmpId){
    		//根据员工id  查询工程区域
    		List<Integer> listPre = employeeDao.findEngineIdsByEmpId(Integer.parseInt(loginUserEmpId));

                List<Integer> list = new ArrayList<Integer>(new HashSet<Integer>(listPre.size()>0?listPre:new ArrayList<Integer>()));
                if (null != list && list.size() > 1) {


                    //多个工程部,  默认空
                    elactricationList.add(model);
                    //
                    List<DropModel> list2 = getElacListWithUserConditons(list);

                    for (DropModel dropModel : list2) {
                        elactricationList.add(dropModel);
                    }
                    return elactricationList;

                } else if (list.size() == 1) {

                    //只有一个工程部 ,查询该工程部 显示第一个
                    String label = getElacLabel(String.valueOf(list.get(0)), "");
                    model.setLabel(label);
                    model.setValue(String.valueOf(list.get(0)));
                    elactricationList.add(model);
                    //
                    List<DropModel> list2 = getElacListWithUserConditons(list);

                    for (DropModel dropModel : list2) {
                        if (dropModel.getValue().equals(model.getValue())) {
                            continue;
                        }
                        elactricationList.add(dropModel);
                    }
                    return elactricationList;


                } else {
                    //没有empId  查询全部 ,第一个默认空
                    elactricationList.add(model);
                    //
                    List<DropModel> list2 = getElacList();

                    for (DropModel dropModel : list2) {
                        elactricationList.add(dropModel);
                    }
                    return elactricationList;

                }



    	}else{
    		
    		//没有empId  查询全部 ,第一个默认空
			elactricationList.add(model);
			//
			List<DropModel> list2 = getElacList();
			
			for (DropModel dropModel : list2) {
				elactricationList.add(dropModel);
			}
		return 	 elactricationList;	
    		
    		
    	}
    	
    	
    
    }
    /**
     * 当前用户有什么区域 就显示什么区域
     * @return
     */
    public static List<DropModel> getEngineListWithUserConditionsForBiddenChange() {
    	List<DropModel> elactricationList  = new  ArrayList<DropModel>();
    	DropModel model = new DropModel();
    	model.setValue("");
    	model.setLabel("");
    	String loginUserEmpId = UserUtils.getUser().getEmpId();
    	if(null!=loginUserEmpId){
    		//根据员工id  查询工程区域
    		List<Integer> list = employeeDao.findEngineIdsByEmpId(Integer.parseInt(loginUserEmpId));
    		if(null!=list&&list.size()>0){
    			//如果有一个 默认这一个
    			//当前登录的有哪些区域就查哪些区域
    			//根据当前登录的人的用户id查询到的工程部id 查询工程部list
    			List<DropModel> list2 = getElacListWithUserConditons(list);
    			for (DropModel dropModel : list2) {
    				elactricationList.add(dropModel);
    			}
    			return 	 elactricationList;
    		}else{
        		//没有区域  查询全部 ,第一个默认空
        		elactricationList.add(model);
        		//
        		List<DropModel> list2 = getElacList();
        		for (DropModel dropModel : list2) {
        			elactricationList.add(dropModel);
        		}
        		return 	 elactricationList;	
    		}
    	}else{
    		//没有empId  查询全部 ,第一个默认空
    		elactricationList.add(model);
    		//
    		List<DropModel> list2 = getElacList();
    		for (DropModel dropModel : list2) {
    			elactricationList.add(dropModel);
    		}
    		return 	 elactricationList;	
    	}
    }
    
    
    public static List<DropModel> getEngineListWithUserConditionsForBiddenChangeForOrder() {
    	List<DropModel> elactricationList  = new  ArrayList<DropModel>();
    	DropModel model = new DropModel();
    	model.setValue("");
    	model.setLabel("");
    	String loginUserEmpId = UserUtils.getUser().getEmpId();
    	if(null!=loginUserEmpId){
    		//根据员工id  查询工程区域
    		List<Integer> listPre = employeeDao.findEngineIdsByEmpId(Integer.parseInt(loginUserEmpId));
            List<Integer> list = new ArrayList<Integer>(new HashSet<Integer>(listPre));
    		if(null!=list&&list.size()>0){
    			if(list.size()>1){
    				//如果该用户有多个工程部, 默认空
    				elactricationList.add(model);
    			}
    			//如果有一个 默认这一个
    			//当前登录的有哪些区域就查哪些区域
    			//根据当前登录的人的用户id查询到的工程部id 查询工程部list
    			List<DropModel> list2 = getElacListWithUserConditons(list);
    			for (DropModel dropModel : list2) {
    				elactricationList.add(dropModel);
    			}
    			return 	 elactricationList;
    		}else{
        		//没有区域  查询全部 ,第一个默认空
        		elactricationList.add(model);
        		//
        		List<DropModel> list2 = getAllElacList();
        		for (DropModel dropModel : list2) {
        			elactricationList.add(dropModel);
        		}
        		return 	 elactricationList;	
    		}
    	}else{
    		//没有empId  查询全部 ,第一个默认空
    		elactricationList.add(model);
    		//
    		List<DropModel> list2 = getAllElacList();
    		for (DropModel dropModel : list2) {
    			elactricationList.add(dropModel);
    		}
    		return 	 elactricationList;	
    	}
    }
    
    
    
    
    public static List<DropModel> getEmployeeListByStoreId(String value) {
        BizEmployee employee = new BizEmployee();
        if (value != null && !value.equals("")) {
            employee.setStoreid(value);
        }
        List<DropModel> employeeList = employeeDao.findEmployeeList(employee);
        return employeeList;
    }

    public static List<DropModel> getLeaderList(String value) {
        BizEmployee employee = new BizEmployee();
        List<DropModel> employeeList = employeeDao.findLeaderList(employee);
        return employeeList;
    }
    
    public static List<DropModel> getEmployeeListByConditions(String empTypes, String workTypes, String unauth) {
    	String storeId = UserUtils.getUser().getStoreId();
    	if(storeId!=null){
    		return getEmployeeListByConditionsNew(empTypes, workTypes, unauth,storeId);
    	}
        return getEmployeeListByConditionsNew(empTypes, workTypes, unauth,"");
    }
    
    /**
     * 
     * getEmployeeListByConditions:(这里用一句话描述这个方法的作用). <br/>
     * 多个条件并行查询
     * 如果unauth=true  && storeIds!= "" 按照storeIds查询
     * 如果unauth=false && storeIds!= "" 按照storeIds查询
     * 如果unauth=true  && storeIds== "" 按照权限的值查询
     * 如果unauth=false  && storeIds== "" 按照权限的值查询
     * @param empTypes  员工类型,多个用逗号隔开
     * @param workTypes 员工类型,多个用逗号隔开
     * @param unauth    是否根据权限设置
     * @param storeIds  门店id,多个用逗号隔开 对应officeId
     * @return
     * @since JDK 1.6
     */
    public static List<DropModel> getEmployeeListByConditionsNew(String empTypes, String workTypes, String unauth, String storeIds) {
        Map<String, String> map = new HashMap<String, String>();
        List<Office> officeList = null;
        String officeIds = "";
        Set<String> officeIdSet = null;
        // 如果不控制权限,则查询所有门店
        if ("true".equals(unauth)) {
            officeList = UserUtils.getOfficeAllList();
        } else {
            officeList = UserUtils.getOfficeList();
        }
        if (!"".equals(storeIds)) {
            officeIdSet = new HashSet<String>(Arrays.asList(storeIds.split(",")));
        }
        if (officeIdSet != null) {
            for (Office office : officeList) {
                if (officeIdSet.contains(office.getId().toString())) {
                    officeIds += office.getId() + ",";
                }
            }
        } else {
            for (Office office : officeList) {
                officeIds += office.getId() + ",";
            }
        }
        if (!"".equals(officeIds)) {
            if(officeIds.lastIndexOf(",")>0){
                officeIds = officeIds.substring(0,officeIds.length()-1);    
            }
            map.put("officeIds", officeIds);
        }
        if (empTypes != null && !empTypes.equals("")) {
            map.put("empTypes", empTypes);
        }
        if (workTypes != null && !workTypes.equals("")) {
            map.put("workTypes", workTypes);
        }
        List<DropModel> employeeList = employeeDao.findEmployeeListByCondition(map);
        return employeeList;
    }

    public static void main(String[] args) {
        String aString = "a,";
        if(aString.lastIndexOf(",")>0){
            aString = aString.substring(0,aString.length()-1);    
        }
        System.out.println(aString);
        
        aString = ",";
        if(aString.lastIndexOf(",")>0){
            aString = aString.substring(0,aString.length()-1);    
        }
        System.out.println(aString);
        
        aString = ",a";
        if(aString.lastIndexOf(",")>0){
            aString = aString.substring(0,aString.length()-1);    
        }
        System.out.println(aString);
    }
    
    
    public static String getEmployeeNameByIds(String ids) {
        String defaultValue = "";
        if (StringUtils.isNotBlank(ids)) {
            Map map = new HashMap<String, String>();
            map.put("ids", ids);
            List<DropModel> employeeList = employeeDao.findAllListByIds(map);
            for (DropModel emp : employeeList) {
                defaultValue += emp.getLabel() + ",";
            }
           // System.out.println("------------------------" + defaultValue + ",employeeList.size:" + employeeList.size() + "-----------");
        }
        return defaultValue;
    }

    public static String getEmployeeNameByDepartmentId(String id,String type) {
        String defaultValue = "";
        if (StringUtils.isNotBlank(id)) {
            Map<Object, String> map = new HashMap<Object, String>();
            map.put("departmentId", id);
            map.put("type", type);
            List<DropModel> employeeList = employeeDao.findAllListByDepartmentId(map);
            for (DropModel emp : employeeList) {
            	if (emp != null && emp.getLabel() != null) {
            		defaultValue += emp.getLabel() + ",";
				}
            }
            //System.out.println("------------------------" + defaultValue + ",employeeList.size:" + employeeList.size() + "-----------");
        }
        return defaultValue;
    }
    
    public static List<DropModel> getEmployeeListByIds(String ids) {
        List<DropModel> employeeList = new ArrayList<DropModel>();
        if (StringUtils.isNotBlank(ids)) {
            Map map = new HashMap<String, String>();
            map.put("ids", ids);
            employeeList = employeeDao.findAllListByIds(map);
        }
        return employeeList;
    }
    
    
    public static List<DropModel> getEmployeeListByDepartmentId(String id,String type) {
        List<DropModel> employeeList = new ArrayList<DropModel>();
        if (StringUtils.isNotBlank(id)) {
            Map<Object, String> map = new HashMap<Object, String>();
            map.put("departmentId", id);
            map.put("type", type);
            employeeList = employeeDao.findAllListByDepartmentId(map);
        }
        return employeeList;
    }

    public static List<DropModel> getElacList() {
        List<DropModel> elactricationList = engineeringDepartmentDao.findEngDepList();
        return elactricationList;
    }
    
    public static List<DropModel> getAllElacList() {
        List<DropModel> elactricationList = engineeringDepartmentDao.findAllEngDepList();
        return elactricationList;
    }
    
    public static List<DropModel> getElacListWithUserConditons(List<Integer>ids) {
    	List<DropModel> elactricationList = engineeringDepartmentDao.findEngDepListWithUserConditions(ids);
    	return elactricationList;
    }
    public static List<DropModel> findEngDepListOnlyIndustry() {
    	List<DropModel> elactricationList = engineeringDepartmentDao.findEngDepListOnlyIndustry();
    	return elactricationList;
    }

    // /**
    // * 返回字典列表（JSON）
    // * @param type
    // * @return
    // */
    // public static String getStoreListJson(String type){
    // return JsonMapper.toJsonString(getStoreList(type));
    // }

    public static List<Office> getNowStoreList() {
        List<Office> officeList = UserUtils.getOfficeList();

        return officeList;
    }
}