package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp 
 * 2016-11-17
 */
@SuppressWarnings("serial")
public class RecheckScaleBillCurtain extends DataEntity2<RecheckScaleBillCurtain> {
	private Integer id;

	/**
	 * 复尺单id
	 */
	private Integer recheckScaleBillId;

	/**
	 * 位置
	 */
	private String position;

	/**
	 * 窗帘杆类型
	 */
	private String poleType;

	/**
	 * 杆长
	 */
	private String poleLength;

	/**
	 * 帘布高度
	 */
	private String clothHeight;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建日期时间
	 */
	private Date createDate;
	
	/**
	 * 创建人
	 */
	private String createPeo;

	/**
	 * 更新日期时间
	 */
	private Date updateDate;

	/**
	 * 是否删除
	 */
	private String delFlag;
	
	/**
	 * 更新人
	 */
	private String updatePeo;

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

	public String getPoleType() {
		return poleType;
	}

	public void setPoleType(String poleType) {
		this.poleType = poleType;
	}

	public String getPoleLength() {
		return poleLength;
	}

	public void setPoleLength(String poleLength) {
		this.poleLength = poleLength;
	}

	public String getClothHeight() {
		return clothHeight;
	}

	public void setClothHeight(String clothHeight) {
		this.clothHeight = clothHeight;
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

	public String getUpdatePeo() {
		return updatePeo;
	}

	public void setUpdatePeo(String updatePeo) {
		this.updatePeo = updatePeo;
	}

}