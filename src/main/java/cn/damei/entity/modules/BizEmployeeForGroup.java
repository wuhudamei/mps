
package cn.damei.entity.modules;


public class BizEmployeeForGroup extends BizEmployee {

	private static final long serialVersionUID = 1L;
	private String workTypeName;
	private String managerRealName;
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