package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.constantUtils.PictureTypeContantUtil;

/**
 * Created by joseph on 2017/7/11.
 */
public class ComplaintLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String typeName;
	private String packName;
	private String complaintTo;
	private List<BizOrderComplaintProblemItem> itemList = new ArrayList<>();
	private String problemContent;
	private Date infoDate;// 发送日期时间
	private String msgsndPeriod;// 执行时限
	private Integer datamiao;// 相差的时间数 秒为单位
	private Date dataxiaoshi;// 相差的时间数如果为正说说明没有超时 如果为负数说明超时了
	private String isdatefla;// 相差的时间数如果为正说说明没有超时 如果为负数说明超时了

	/**
	 * replyName,replyDate,replyContent
	 */
	private List<BizOrderComplaintProblemDeal> dealList = new ArrayList<>();

	public List<BizOrderComplaintProblemDeal> getDealList() {
		return dealList;
	}

	public void setDealList(List<BizOrderComplaintProblemDeal> dealList) {
		this.dealList = dealList;
	}

	private Integer problemId;
	private String problemPicType = PictureTypeContantUtil.PICTURE_TYPE_200;

	private String problemHandleManagerPicType = PictureTypeContantUtil.PICTURE_TYPE_109;
	private String problemHandlePqcPicType = PictureTypeContantUtil.PICTURE_TYPE_112;

	private Integer problemHandleId;

	public String getTypeName() {
		return typeName;
	}

	public String getIsdatefla() {
		return isdatefla;
	}

	public void setIsdatefla(String isdatefla) {
		this.isdatefla = isdatefla;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getInfoDate() {
		return infoDate;
	}

	public void setInfoDate(Date infoDate) {
		this.infoDate = infoDate;
	}

	public String getMsgsndPeriod() {
		return msgsndPeriod;
	}

	public void setMsgsndPeriod(String msgsndPeriod) {
		this.msgsndPeriod = msgsndPeriod;
	}

	public Integer getDatamiao() {
		return datamiao;
	}

	public void setDatamiao(Integer datamiao) {
		this.datamiao = datamiao;
	}

	public Date getDataxiaoshi() {
		return dataxiaoshi;
	}

	public void setDataxiaoshi(Date dataxiaoshi) {
		this.dataxiaoshi = dataxiaoshi;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getComplaintTo() {
		return complaintTo;
	}

	public void setComplaintTo(String complaintTo) {
		this.complaintTo = complaintTo;
	}

	public List<BizOrderComplaintProblemItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<BizOrderComplaintProblemItem> itemList) {
		this.itemList = itemList;
	}

	public String getProblemContent() {
		return problemContent;
	}

	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public String getProblemPicType() {
		return problemPicType;
	}

	public void setProblemPicType(String problemPicType) {
		this.problemPicType = problemPicType;
	}

	public String getProblemHandleManagerPicType() {
		return problemHandleManagerPicType;
	}

	public void setProblemHandleManagerPicType(String problemHandleManagerPicType) {
		this.problemHandleManagerPicType = problemHandleManagerPicType;
	}

	public String getProblemHandlePqcPicType() {
		return problemHandlePqcPicType;
	}

	public void setProblemHandlePqcPicType(String problemHandlePqcPicType) {
		this.problemHandlePqcPicType = problemHandlePqcPicType;
	}

	public Integer getProblemHandleId() {
		return problemHandleId;
	}

	public void setProblemHandleId(Integer problemHandleId) {
		this.problemHandleId = problemHandleId;
	}
}
