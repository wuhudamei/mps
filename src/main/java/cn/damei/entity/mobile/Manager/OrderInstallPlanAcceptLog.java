package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class OrderInstallPlanAcceptLog extends DataEntity2<OrderInstallPlanAcceptLog> {

	private Integer orderId; //订单id
	private Integer orderInstallPlanId; //订单安装计划id
	private String acceptType; //验收类型（合格、不合格）
	private Integer orderInstallIteamId; //安装项名称id
	private String orderInstallIteam; //安装项名称
	private String unqualifiedReason; //验收不合格原因
	private String acceptRemarks; //验收备注
	private Integer operaterId; //操作人id
	private String unqualifiedReasonName; //验收不合格原因

	private Integer unqualifiedReasonId; //不合格原因id
	private String unqualifiedReasonConfigure; //不合格原因（配置）


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderInstallPlanId() {
		return orderInstallPlanId;
	}

	public void setOrderInstallPlanId(Integer orderInstallPlanId) {
		this.orderInstallPlanId = orderInstallPlanId;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	public Integer getOrderInstallIteamId() {
		return orderInstallIteamId;
	}

	public void setOrderInstallIteamId(Integer orderInstallIteamId) {
		this.orderInstallIteamId = orderInstallIteamId;
	}

	public String getOrderInstallIteam() {
		return orderInstallIteam;
	}

	public void setOrderInstallIteam(String orderInstallIteam) {
		this.orderInstallIteam = orderInstallIteam;
	}

	public String getUnqualifiedReason() {
		return unqualifiedReason;
	}

	public void setUnqualifiedReason(String unqualifiedReason) {
		this.unqualifiedReason = unqualifiedReason;
	}

	public String getAcceptRemarks() {
		return acceptRemarks;
	}

	public void setAcceptRemarks(String acceptRemarks) {
		this.acceptRemarks = acceptRemarks;
	}

	public Integer getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}

	public String getUnqualifiedReasonName() {
		return unqualifiedReasonName;
	}

	public void setUnqualifiedReasonName(String unqualifiedReasonName) {
		this.unqualifiedReasonName = unqualifiedReasonName;
	}

	public Integer getUnqualifiedReasonId() {
		return unqualifiedReasonId;
	}

	public void setUnqualifiedReasonId(Integer unqualifiedReasonId) {
		this.unqualifiedReasonId = unqualifiedReasonId;
	}

	public String getUnqualifiedReasonConfigure() {
		return unqualifiedReasonConfigure;
	}

	public void setUnqualifiedReasonConfigure(String unqualifiedReasonConfigure) {
		this.unqualifiedReasonConfigure = unqualifiedReasonConfigure;
	}
}
