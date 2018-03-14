package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class BizRecheckScaleBillTaokou extends DataEntity2<BizRecheckScaleBillTaokou> {
	private Integer id;


	private Integer recheckScaleBillId;


	private String position;


	private String packageCover;


	private String holeWidth;


	private String holeHeight;


	private String holeThickness;
	

	private String remarks;
	

	private Date createDate;


	private Date updateDate;


	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecheckScaleBillId() {
		return recheckScaleBillId;
	}

	public void setRecheckScaleBillId(Integer recheckScaleBillId) {
		this.recheckScaleBillId = recheckScaleBillId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPackageCover() {
		return packageCover;
	}

	public void setPackageCover(String packageCover) {
		this.packageCover = packageCover;
	}

	public String getHoleWidth() {
		return holeWidth;
	}

	public void setHoleWidth(String holeWidth) {
		this.holeWidth = holeWidth;
	}

	public String getHoleHeight() {
		return holeHeight;
	}

	public void setHoleHeight(String holeHeight) {
		this.holeHeight = holeHeight;
	}

	public String getHoleThickness() {
		return holeThickness;
	}

	public void setHoleThickness(String holeThickness) {
		this.holeThickness = holeThickness;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}
