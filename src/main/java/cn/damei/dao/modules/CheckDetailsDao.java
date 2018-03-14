
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.CheckDetails;
import cn.damei.entity.modules.BizQcBill;


@MyBatisDao
public interface CheckDetailsDao extends CrudDao<CheckDetails> {


	List<BizQcBill> detailsList(Integer orderId);
	
}