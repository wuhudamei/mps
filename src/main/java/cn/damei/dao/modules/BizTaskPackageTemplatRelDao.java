/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageTemplatRel;
import cn.damei.entity.modules.BizTaskPackageTemplatRelProcedure;

/**
 * 任务包模板管理DAO接口
 * @author wangchao
 * @version 2016-09-03
 */
@MyBatisDao
public interface BizTaskPackageTemplatRelDao extends CrudDao<BizTaskPackageTemplatRel> {
	/**
	 * 根据任务模板id查询
	 * @param taskPackageTemplatId
	 * @return
	 */
	public List<BizTaskPackageTemplatRel> findByTaskPackageTemplateId(String taskPackageTemplatId);
	
	/**
	 * 根据任务模板id查询
	 * @param taskPackageTemplatId
	 * @return
	 */
	public List<BizTaskPackageTemplatRelProcedure> findByTaskPackageRelProcedureTemplateId(String taskPackageTemplatId);
	
	/**
	 * 批量添加
	 * @param list
	 */
	public void batchInsert(List<BizTaskPackageTemplatRel> list);
	/**
	 * 批量删除
	 * @param list
	 */
	public void batchDeleteByTemplatId(Map<String,Object> map);

	public List<BizTaskPackageTemplatRel> getByProcedureNo(@Param("taskPackageTemplatId") 
		String taskPackageTemplatId, @Param("list") List<String> list);

	public List<BizTaskPackageTemplatRel> getList(String value, String defaultValue);

	public List<BizTaskPackageTemplatRel> getAllList(String value);

	public List<BizTaskPackageTemplatRel> getByTaskPackageTemplatId(String value);
}