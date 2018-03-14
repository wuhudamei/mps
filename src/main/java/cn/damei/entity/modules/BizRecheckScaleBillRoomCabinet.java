package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class BizRecheckScaleBillRoomCabinet extends DataEntity2<BizRecheckScaleBillRoomCabinet> {
	private Integer id;


	private Integer recheckScaleBillId;


	private String position;


	private String bathroomCabinetModel;


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

	public String getBathroomCabinetModel() {
		return bathroomCabinetModel;
	}

	public void setBathroomCabinetModel(String bathroomCabinetModel) {
		this.bathroomCabinetModel = bathroomCabinetModel;
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
