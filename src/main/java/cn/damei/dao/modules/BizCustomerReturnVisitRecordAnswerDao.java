
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecordAnswer;


@MyBatisDao
public interface BizCustomerReturnVisitRecordAnswerDao extends CrudDao2<BizCustomerReturnVisitRecordAnswer> {

	List<BizCustomerReturnVisitRecordAnswer> getListByRecordId(Integer returnVisitRecordId);

	List<BizCustomerReturnVisitRecordAnswer> getListByRecordIds(List<Integer> list);

	void insertBatch(List<BizCustomerReturnVisitRecordAnswer> list);
}