
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;


@MyBatisDao
public interface BizPmOwnpayCnfgSnapDao extends CrudDao2<BizPmOwnpayCnfgSnap> {

	List<BizPmOwnpayCnfgSnap> findListByMap(Map<String, Object> map2);

	void deleteByOrderId(Integer id);

	List<BizPmOwnpayCnfgSnap> findByOrderId(Integer id);

	void insertList(List<BizPmOwnpayCnfgSnap> list);
	
	
}