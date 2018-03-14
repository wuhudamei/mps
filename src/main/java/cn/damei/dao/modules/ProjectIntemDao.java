/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.modules.ProjectIntem;
import cn.damei.entity.modules.ProjectItemPrice;
import cn.damei.entity.modules.ProjectItemType;

/**
 * 施工项DAO接口
 * @author 梅浩
 * @version 2016-11-15
 */
@MyBatisDao
public interface ProjectIntemDao extends CrudDao<ProjectIntem> {
	
	/**
	 * 查询启用的施工项分类
	 * @return
	 */
	public List<ProjectItemType>findProjectItemTypeList();
	
	
	//编号 SG 
	public void saveCode();
	public  ReCheckCode  getCode();
	public  void updateCode(ReCheckCode code);


	public List<ProjectItemPrice> findProjectItemPrice();
	
}