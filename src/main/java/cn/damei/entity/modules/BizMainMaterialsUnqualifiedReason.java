/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.List;

/**
 * 主材安装项验收不合格原因配置Entity
 * @author wyb
 * @version 2018-01-23
 */
public class BizMainMaterialsUnqualifiedReason extends DataEntity2<BizMainMaterialsUnqualifiedReason> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private String projectMode;		// 工程模式
	private String installMode;		// 安装模式
	private Integer mainMaterialsInstallItemId;		// 主材安装项
	private String mainMaterialsInstallItem;		// 主材安装项名称
	private String unqualifiedReason;		// 不合格原因
	private String status;		// 是否启用

	private String storeName;		//门店名称
	private String projectModeName;		//工程模式名称
	private String installModeName;		//安装模式名称
	private String operatorName;		//操作人名称
	private Integer mainMaterialsInstallItemIdStop;		//主材安装项【停用】
	private List<Integer> mainMaterialsInstallItemIdList = null; //主材安装项id集合

	public BizMainMaterialsUnqualifiedReason() {
		super();
	}

	public BizMainMaterialsUnqualifiedReason(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public Integer getMainMaterialsInstallItemId() {
		return mainMaterialsInstallItemId;
	}

	public void setMainMaterialsInstallItemId(Integer mainMaterialsInstallItemId) {
		this.mainMaterialsInstallItemId = mainMaterialsInstallItemId;
	}
	
	public String getMainMaterialsInstallItem() {
		return mainMaterialsInstallItem;
	}

	public void setMainMaterialsInstallItem(String mainMaterialsInstallItem) {
		this.mainMaterialsInstallItem = mainMaterialsInstallItem;
	}
	
	public String getInstallMode() {
		return installMode;
	}

	public void setInstallMode(String installMode) {
		this.installMode = installMode;
	}
	
	public String getUnqualifiedReason() {
		return unqualifiedReason;
	}

	public void setUnqualifiedReason(String unqualifiedReason) {
		this.unqualifiedReason = unqualifiedReason;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public String getInstallModeName() {
		return installModeName;
	}

	public void setInstallModeName(String installModeName) {
		this.installModeName = installModeName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getMainMaterialsInstallItemIdStop() {
		return mainMaterialsInstallItemIdStop;
	}

	public void setMainMaterialsInstallItemIdStop(Integer mainMaterialsInstallItemIdStop) {
		this.mainMaterialsInstallItemIdStop = mainMaterialsInstallItemIdStop;
	}

	public List<Integer> getMainMaterialsInstallItemIdList() {
		return mainMaterialsInstallItemIdList;
	}

	public void setMainMaterialsInstallItemIdList(List<Integer> mainMaterialsInstallItemIdList) {
		this.mainMaterialsInstallItemIdList = mainMaterialsInstallItemIdList;
	}
}