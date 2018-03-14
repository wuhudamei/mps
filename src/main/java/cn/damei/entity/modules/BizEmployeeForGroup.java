/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

/**
 * 员工信息Entity
 * 
 * @author qhy
 * @version 2016-08-24
 */
public class BizEmployeeForGroup extends BizEmployee {

	private static final long serialVersionUID = 1L;
	private String workTypeName;// 工种
	private String managerRealName; // 推荐项目经理名称
    public String getWorkTypeName() {
        return workTypeName;
    }
    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }
    public String getManagerRealName() {
        return managerRealName;
    }
    public void setManagerRealName(String managerRealName) {
        this.managerRealName = managerRealName;
    }

}