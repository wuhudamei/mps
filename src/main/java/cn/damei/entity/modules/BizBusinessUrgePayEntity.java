
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;


public class BizBusinessUrgePayEntity extends DataEntity<BizBusinessUrgePayEntity> {
	
	private static final long serialVersionUID = 1L;
	private String relatedBusinessType;
	private String relatedBusinessIdInt;
	private String urgePayType;
	private String urgePayContent;
	private String urgePayChannel;
	private String status;
	private Date statusDatetime;
	private String statusOperatorEmployeeId;
	private String urgeTargetName;
	private String urgeTargetPhone;
	private String urgeTargetName2;
	private String urgeTargetPhone2;
	
	
	public String getUrgeTargetName2() {
		return urgeTargetName2;
	}

	public void setUrgeTargetName2(String urgeTargetName2) {
		this.urgeTargetName2 = urgeTargetName2;
	}

	public String getUrgeTargetPhone2() {
		return urgeTargetPhone2;
	}

	public void setUrgeTargetPhone2(String urgeTargetPhone2) {
		this.urgeTargetPhone2 = urgeTargetPhone2;
	}

	public BizBusinessUrgePayEntity() {
		super();
	}

	public BizBusinessUrgePayEntity(String id){
		super(id);
	}

	@Length(min=0, max=10, message="related_business_type长度必须介于 0 和 10 之间")
	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}

	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}
	
	@Length(min=0, max=11, message="related_business_id_int长度必须介于 0 和 11 之间")
	public String getRelatedBusinessIdInt() {
		return relatedBusinessIdInt;
	}

	public void setRelatedBusinessIdInt(String relatedBusinessIdInt) {
		this.relatedBusinessIdInt = relatedBusinessIdInt;
	}
	
	@Length(min=0, max=10, message="urge_pay_type长度必须介于 0 和 10 之间")
	public String getUrgePayType() {
		return urgePayType;
	}

	public void setUrgePayType(String urgePayType) {
		this.urgePayType = urgePayType;
	}
	
	@Length(min=0, max=1000, message="urge_pay_content长度必须介于 0 和 1000 之间")
	public String getUrgePayContent() {
		return urgePayContent;
	}

	public void setUrgePayContent(String urgePayContent) {
		this.urgePayContent = urgePayContent;
	}
	
	@Length(min=0, max=10, message="urge_pay_channel长度必须介于 0 和 10 之间")
	public String getUrgePayChannel() {
		return urgePayChannel;
	}

	public void setUrgePayChannel(String urgePayChannel) {
		this.urgePayChannel = urgePayChannel;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	
	@Length(min=0, max=11, message="״̬长度必须介于 0 和 11 之间")
	public String getStatusOperatorEmployeeId() {
		return statusOperatorEmployeeId;
	}

	public void setStatusOperatorEmployeeId(String statusOperatorEmployeeId) {
		this.statusOperatorEmployeeId = statusOperatorEmployeeId;
	}
	
	@Length(min=0, max=20, message="urge_target_name长度必须介于 0 和 20 之间")
	public String getUrgeTargetName() {
		return urgeTargetName;
	}

	public void setUrgeTargetName(String urgeTargetName) {
		this.urgeTargetName = urgeTargetName;
	}
	
	@Length(min=0, max=11, message="urge_target_phone长度必须介于 0 和 11 之间")
	public String getUrgeTargetPhone() {
		return urgeTargetPhone;
	}

	public void setUrgeTargetPhone(String urgeTargetPhone) {
		this.urgeTargetPhone = urgeTargetPhone;
	}
	
}