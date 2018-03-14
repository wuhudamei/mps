
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.TreeDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Category;


@MyBatisDao
public interface CategoryDao extends TreeDao<Category> {
	
	public List<Category> findModule(Category category);
	





	public List<Category> findByModule(String module);




	
	public List<Category> findByParentId(String parentId, String isMenu);





	public List<Category> findByParentIdAndSiteId(Category entity);
	
	public List<Map<String, Object>> findStats(String sql);




	











	
}
