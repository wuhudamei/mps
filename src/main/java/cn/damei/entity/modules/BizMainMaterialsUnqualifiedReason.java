
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.List;


public class BizMainMaterialsUnqualifiedReason extends DataEntity2<BizMainMaterialsUnqualifiedReason> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String installMode;
	private Integer mainMaterialsInstallItemId;
	private String mainMaterialsInstallItem;
	private String unqualifiedReason;
	private String status;

	private String storeName;
	private String projectModeName;
	private String installModeName;
	private String operatorName;
	private Integer mainMaterialsInstallItemIdStop;
	private List<Integer> mainMaterialsInstallItemIdList = null;

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