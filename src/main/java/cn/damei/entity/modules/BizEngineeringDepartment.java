/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程部管理Entity
 * 
 * @author haven
 * @version 2016-09-05
 */
public class BizEngineeringDepartment extends DataEntity<BizEngineeringDepartment> {

    private static final long serialVersionUID = 1L;
    private String name; // 工程部名称
    private String groupId; // 工人组
    private String leader; // 负责人
    private String jobDispatcher; // 派工调度员
    private String materialDispatcher; // 材料调度员
    private String inspector; // 质检员
    private String manager; // 项目经理
    private String orderDispatcher;
    private String settlementClerk;
    private String receptionist;
    private String budgeteer;
    
    private String jobDispatcherNon; // 派工调度员
    private String materialDispatcherNon; // 材料调度员
    private String inspectorNon; // 质检员
    private String managerNon; // 项目经理
    private String projectMode; // 工程模式 -- '1-产业模式；2-传统模式；3-全部

    private String orderDispatcherNon;      //派单员  
    private String settlementClerkNon;      //结算员 
    private String receptionistNon;         //接单员
    private String budgeteerNon;			//预算员
    
    private String storeId; // 门店

    public BizEngineeringDepartment() {
        super();
    }

    public BizEngineeringDepartment(String id) {
        super(id);
    }

    @Length(min = 0, max = 255, message = "工程部名称长度必须介于 0 和 255 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 255, message = "工人组长度必须介于 0 和 255 之间")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Length(min = 0, max = 255, message = "负责人长度必须介于 0 和 255 之间")
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Length(min = 0, max = 255, message = "派工调度员长度必须介于 0 和 255 之间")
    public String getJobDispatcher() {
        return jobDispatcher;
    }

    public void setJobDispatcher(String jobDispatcher) {
        this.jobDispatcher = jobDispatcher;
    }

    @Length(min = 0, max = 255, message = "材料调度员长度必须介于 0 和 255 之间")
    public String getMaterialDispatcher() {
        return materialDispatcher;
    }

    public void setMaterialDispatcher(String materialDispatcher) {
        this.materialDispatcher = materialDispatcher;
    }

    @Length(min = 0, max = 255, message = "质检员长度必须介于 0 和 255 之间")
    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

   // @Length(min = 0, max = 255, message = "项目经理长度必须介于 0 和 255 之间")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Length(min = 0, max = 64, message = "门店长度必须介于 0 和 64 之间")
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getJobDispatcherNon() {
        return jobDispatcherNon;
    }

    public void setJobDispatcherNon(String jobDispatcherNon) {
        this.jobDispatcherNon = null;
    }

    public String getMaterialDispatcherNon() {
        return materialDispatcherNon;
    }

    public void setMaterialDispatcherNon(String materialDispatcherNon) {
        this.materialDispatcherNon = null;
    }

    public String getInspectorNon() {
        return inspectorNon;
    }

    public void setInspectorNon(String inspectorNon) {
        this.inspectorNon = null;
    }

    public String getManagerNon() {
        return managerNon;
    }

    public void setManagerNon(String managerNon) {
        this.managerNon = null;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

	public String getOrderDispatcher() {
		return orderDispatcher;
	}

	public void setOrderDispatcher(String orderDispatcher) {
		this.orderDispatcher = orderDispatcher;
	}

	public String getSettlementClerk() {
		return settlementClerk;
	}

	public void setSettlementClerk(String settlementClerk) {
		this.settlementClerk = settlementClerk;
	}

	public String getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(String receptionist) {
		this.receptionist = receptionist;
	}

	public String getBudgeteer() {
		return budgeteer;
	}

	public void setBudgeteer(String budgeteer) {
		this.budgeteer = budgeteer;
	}

	public String getOrderDispatcherNon() {
		return orderDispatcherNon;
	}

	public void setOrderDispatcherNon(String orderDispatcherNon) {
		this.orderDispatcherNon = orderDispatcherNon;
	}

	public String getSettlementClerkNon() {
		return settlementClerkNon;
	}

	public void setSettlementClerkNon(String settlementClerkNon) {
		this.settlementClerkNon = settlementClerkNon;
	}

	public String getReceptionistNon() {
		return receptionistNon;
	}

	public void setReceptionistNon(String receptionistNon) {
		this.receptionistNon = receptionistNon;
	}

	public String getBudgeteerNon() {
		return budgeteerNon;
	}

	public void setBudgeteerNon(String budgeteerNon) {
		this.budgeteerNon = budgeteerNon;
	}
}