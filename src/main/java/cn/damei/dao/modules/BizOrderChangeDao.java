package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderChange;


@MyBatisDao
public interface BizOrderChangeDao extends CrudDao2<BizOrderChange>{
	
	public BizOrderChange queryOrderChangeByParam(Map<String,Object> param);

}
