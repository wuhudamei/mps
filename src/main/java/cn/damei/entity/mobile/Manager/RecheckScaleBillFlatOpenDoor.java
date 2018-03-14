package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp 
 * 平拉门
 * biz_recheck_scale_bill_flat_open_door
 * 2016-11-17
 */
@SuppressWarnings("serial")
public class RecheckScaleBillFlatOpenDoor extends DataEntity2<RecheckScaleBillFlatOpenDoor> {
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
	 * 内外开方向
	 */
	private String inOutOpen;
	
	/**
	 * 包套方式
	 */
	private String packageCover;
	
	/**
	 * 开启方向
	 */
	private String openDirection;

	
	/**
	 * 宽
	 */
	private String width;
	
	/**
	 * 高
	 */
	private String height;
	
	/**
	 * 厚
	 */
	private String thickness;

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
	 * 更新人
	 */
	private String updatePeo;

	/**
	 * 是否删除
	 */
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

	public String getInOutOpen() {
		return inOutOpen;
	}

	public void setInOutOpen(String inOutOpen) {
		this.inOutOpen = inOutOpen;
	}

	public String getPackageCover() {
		return packageCover;
	}

	public void setPackageCover(String packageCover) {
		this.packageCover = packageCover;
	}

	public String getOpenDirection() {
		return openDirection;
	}

	public void setOpenDirection(String openDirection) {
		this.openDirection = openDirection;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
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
