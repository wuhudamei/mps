/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * llpEntity
 * @author llp
 * @version 2016-10-11
 */
public class BizNodePlanPic extends DataEntity2<BizNodePlanPic> {
	
	private static final long serialVersionUID = 1L;
	private String nodePlanId;		// 节点计划id -- '
	private String picUrl;		// 图片url -- '
	
	public BizNodePlanPic() {
		super();
	}

	public BizNodePlanPic(Integer id){
		super(id);
	}

	@Length(min=0, max=11, message="节点计划id -- '长度必须介于 0 和 11 之间")
	public String getNodePlanId() {
		return nodePlanId;
	}

	public void setNodePlanId(String nodePlanId) {
		this.nodePlanId = nodePlanId;
	}
	
	@Length(min=0, max=64, message="图片url -- '长度必须介于 0 和 64 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}