
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcCheckItem;
import cn.damei.entity.modules.BizQcCheckKind;


@MyBatisDao
public interface BizQcCheckItemDao extends CrudDao2<BizQcCheckItem> {

	List<BizQcCheckKind> findCheckKind(BizQcCheckKind kind);
	

	String findName(int a);

	List<Map<String,String>> taskPackageTemplat(Integer id);

    int updatecheckItem(BizQcCheckItem bizQcCheckItem);
}