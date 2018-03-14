
package cn.damei.common.persistence;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


public abstract class DataEntity2<T> extends BaseEntity2<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;
	protected User createBy;
	protected Date createDate;
	protected User updateBy;
	protected Date updateDate;
	protected String delFlag;
	protected String frontSort="";
	
	public String getFrontSort() {
		return frontSort;
	}

	public void setFrontSort(String frontSort) {
		this.frontSort = frontSort;
	}

	public DataEntity2() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity2(Integer id) {
		super(id);
	}
	

	@Override
	public void preInsert(){

		if (!this.isNewRecord){

		}
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	

	@Override
	public void preUpdate(){
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
		}
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
