
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Article;
import cn.damei.entity.modules.Category;


@MyBatisDao
public interface ArticleDao extends CrudDao<Article> {
	
	public List<Article> findByIdIn(String[] ids);



	
	public int updateHitsAddOne(String id);



	
	public int updateExpiredWeight(Article article);
	
	public List<Category> findStats(Category category);



	
}
