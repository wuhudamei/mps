
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTaskPackageTemplatRel;
import cn.damei.entity.modules.BizTaskPackageTemplatRelProcedure;


@MyBatisDao
public interface BizTaskPackageTemplatRelDao extends CrudDao<BizTaskPackageTemplatRel> {

	public List<BizTaskPackageTemplatRel> findByTaskPackageTemplateId(String taskPackageTemplatId);
	

	public List<BizTaskPackageTemplatRelProcedure> findByTaskPackageRelProcedureTemplateId(String taskPackageTemplatId);
	

	public void batchInsert(List<BizTaskPackageTemplatRel> list);

	public void batchDeleteByTemplatId(Map<String,Object> map);

	public List<BizTaskPackageTemplatRel> getByProcedureNo(@Param("taskPackageTemplatId") 
		String taskPackageTemplatId, @Param("list") List<String> list);

	public List<BizTaskPackageTemplatRel> getList(String value, String defaultValue);

	public List<BizTaskPackageTemplatRel> getAllList(String value);

	public List<BizTaskPackageTemplatRel> getByTaskPackageTemplatId(String value);
}