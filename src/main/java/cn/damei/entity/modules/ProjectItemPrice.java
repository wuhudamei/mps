
package cn.damei.entity.modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity;



public class ProjectItemPrice extends DataEntity<ProjectItemPrice> {
	
	private static final long serialVersionUID = 1L;
	private Integer itemPriceId;
	private Integer storeId;
	private Integer projectIntemId;
	private String projectIntemPrice;
	private Integer projectIntemVersion;
	private Date effectDate;
	private String projectIntemPriceRemarks;
	private String projectIntemCode;
	private String projectIntemName;
	private String valuationMethod;
	private String projectIntemCostPrice;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String getEffectDateString() {
        String format = sdf.format(effectDate);
        return format;
	}
	public String getProjectIntemCode() {
		return projectIntemCode;
	}

	public void setProjectIntemCode(String projectIntemCode) {
		this.projectIntemCode = projectIntemCode;
	}

	public String getProjectIntemName() {
		return projectIntemName;
	}

	public void setProjectIntemName(String projectIntemName) {
		this.projectIntemName = projectIntemName;
	}

	public String getValuationMethod() {
		return valuationMethod;
	}

	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public ProjectItemPrice() {
		super();
	}

	public ProjectItemPrice(String id){
		super(id);
	}

	public Integer getItemPriceId() {
		return itemPriceId;
	}

	public void setItemPriceId(Integer itemPriceId) {
		this.itemPriceId = itemPriceId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProjectIntemId() {
		return projectIntemId;
	}

	public void setProjectIntemId(Integer projectIntemId) {
		this.projectIntemId = projectIntemId;
	}

	public String getProjectIntemPrice() {
		return projectIntemPrice;
	}

	public void setProjectIntemPrice(String projectIntemPrice) {
		this.projectIntemPrice = projectIntemPrice;
	}

	public String getProjectIntemCostPrice() {
		return projectIntemCostPrice;
	}

	public void setProjectIntemCostPrice(String projectIntemCostPrice) {
		this.projectIntemCostPrice = projectIntemCostPrice;
	}

	public Integer getProjectIntemVersion() {
		return projectIntemVersion;
	}

	public void setProjectIntemVersion(Integer projectIntemVersion) {
		this.projectIntemVersion = projectIntemVersion;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	@Length(min=0, max=255, message="价格备注 -- '长度必须介于 0 和 255 之间")
	public String getProjectIntemPriceRemarks() {
		return projectIntemPriceRemarks;
	}

	public void setProjectIntemPriceRemarks(String projectIntemPriceRemarks) {
		this.projectIntemPriceRemarks = projectIntemPriceRemarks;
	}

}