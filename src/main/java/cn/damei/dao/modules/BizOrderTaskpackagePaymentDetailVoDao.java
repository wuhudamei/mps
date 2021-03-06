package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailVo;

@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailVoDao extends CrudDao2<BizOrderTaskpackagePaymentDetailVo>{

	List<BizOrderTaskpackagePaymentDetailVo> findPaymentDatailsBySummaryId(Integer id);
	
}
