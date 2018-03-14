
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.GenTable;
import cn.damei.entity.modules.GenTableColumn;


@MyBatisDao
public interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {


	List<GenTable> findTableList(GenTable genTable);


	List<GenTableColumn> findTableColumnList(GenTable genTable);
	

	List<String> findTablePK(GenTable genTable);
	
}
