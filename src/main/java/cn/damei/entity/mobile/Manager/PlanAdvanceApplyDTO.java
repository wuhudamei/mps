package cn.damei.entity.mobile.Manager;

import java.io.Serializable;

public class PlanAdvanceApplyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1505096835657989124L;
	
	private OrderInstallPlan orderInstallPlan;// 订单安装项
	private EnginInstall enginInstall;// 订单
	
	public OrderInstallPlan getOrderInstallPlan() {
		return orderInstallPlan;
	}
	public void setOrderInstallPlan(OrderInstallPlan orderInstallPlan) {
		this.orderInstallPlan = orderInstallPlan;
	}
	public EnginInstall getEnginInstall() {
		return enginInstall;
	}
	public void setEnginInstall(EnginInstall enginInstall) {
		this.enginInstall = enginInstall;
	}

}
