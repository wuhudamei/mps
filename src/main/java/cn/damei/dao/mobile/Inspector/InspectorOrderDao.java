package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectOrderVo;

@MyBatisDao
public interface InspectorOrderDao {

	
	public List<InspectOrderVo> findMyOrderInfoByInspectorId(InspectOrderVo vo);
}
