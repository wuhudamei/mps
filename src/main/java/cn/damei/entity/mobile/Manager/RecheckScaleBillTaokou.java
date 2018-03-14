package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class RecheckScaleBillTaokou extends DataEntity2<RecheckScaleBillTaokou> {
	private Integer id;


	private Integer recheckScaleBillId;


	private String position;


	private String packageCover;


	private String holeWidth;


	private String holeHeight;


	private String holeThickness;
	

	private String remarks;
	

	private String createPeo;
	

	private Date createDate;


	private Date updateDate;
	

	private String updatePeo;


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

	public String getCreatePeo() {
		return createPeo;
	}

	public void setCreatePeo(String createPeo) {
		this.createPeo = createPeo;
	}

	public void setUpdatePeo(String updatePeo) {
		this.updatePeo = updatePeo;
	}

	public String getUpdatePeo() {
		return updatePeo;
	}
}
