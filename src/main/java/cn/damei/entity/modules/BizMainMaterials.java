
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMainMaterials extends DataEntity2<BizMainMaterials>
{

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String mainMaterialsNo;
	private String mainMaterialsName;
	private Integer empWorkType;
	private Integer categoryId;
	private String specifications;
	private String measurementUnit;

	private String status;
	private String brands;
	private String picUrl;
	private String isCounted;
	private Integer sortIndex;

	public BizMainMaterials()
	{
		super();
	}

	public BizMainMaterials(Integer id)
	{
		super(id);
	}

	public Integer getStoreId()
	{
		return storeId;
	}

	public void setStoreId(Integer storeId)
	{
		this.storeId = storeId;
	}

	@Length(min = 0, max = 100, message = "材料编号 -- '长度必须介于 0 和 100 之间")
	public String getMainMaterialsNo()
	{
		return mainMaterialsNo;
	}

	public void setMainMaterialsNo(String mainMaterialsNo)
	{
		this.mainMaterialsNo = mainMaterialsNo;
	}

	@Length(min = 0, max = 100, message = "材料名称 -- '长度必须介于 0 和 100 之间")
	public String getMainMaterialsName()
	{
		return mainMaterialsName;
	}

	public void setMainMaterialsName(String mainMaterialsName)
	{
		this.mainMaterialsName = mainMaterialsName;
	}

	public Integer getSortIndex()
	{
		return sortIndex;
	}

	public void setSortIndex(Integer sortIndex)
	{
		this.sortIndex = sortIndex;
	}

	public Integer getEmpWorkType()
	{
		return empWorkType;
	}

	public void setEmpWorkType(Integer empWorkType)
	{
		this.empWorkType = empWorkType;
	}

	public Integer getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	@Length(min = 0, max = 255, message = "规格型号 -- '长度必须介于 0 和 255 之间")
	public String getSpecifications()
	{
		return specifications;
	}

	public void setSpecifications(String specifications)
	{
		this.specifications = specifications;
	}

	@Length(min = 0, max = 25, message = "单位 -- ' value: 4 biz_material_unit 个  sys_dict长度必须介于 0 和 25 之间")
	public String getMeasurementUnit()
	{
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit)
	{
		this.measurementUnit = measurementUnit;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Length(min = 0, max = 255, message = "品牌 -- '长度必须介于 0 和 255 之间")
	public String getBrands()
	{
		return brands;
	}

	public void setBrands(String brands)
	{
		this.brands = brands;
	}

	@Length(min = 0, max = 255, message = "图片路径 -- '长度必须介于 0 和 255 之间")
	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getIsCounted()
	{
		return isCounted;
	}

	public void setIsCounted(String isCounted)
	{
		this.isCounted = isCounted;
	}
}