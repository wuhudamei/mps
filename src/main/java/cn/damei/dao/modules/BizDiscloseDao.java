package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizDisclose;

/**
 * 订单交底
 * @author llp
 */
@MyBatisDao
public interface BizDiscloseDao extends CrudDao2<BizDisclose>{

	List<BizDisclose> getByList();

}
