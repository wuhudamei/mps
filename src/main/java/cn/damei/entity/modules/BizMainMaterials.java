/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材管理Entity
 * 
 * @author qww
 * @version 2016-10-10
 */
public class BizMainMaterials extends DataEntity2<BizMainMaterials>
{

	private static final long serialVersionUID = 1L;
	private Integer storeId; // 门店id -- '
	private String mainMaterialsNo; // 材料编号 -- '
	private String mainMaterialsName; // 材料名称 -- '
	private Integer empWorkType; // 常用工种 -- '
	private Integer categoryId; // 材料类别id -- '
	private String specifications; // 规格型号 -- '
	private String measurementUnit; // 单位 -- ' value: 4 biz_material_unit 个
									// sys_dict
	private String status; // 状态;0:停用，1：启用 -- '
	private String brands; // 品牌 -- '
	private String picUrl; // 图片路径 -- '
	private String isCounted; // 是否计算标准数量
	private Integer sortIndex; // 排序值

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