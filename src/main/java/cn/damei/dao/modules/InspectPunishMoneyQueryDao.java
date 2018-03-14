package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.InspectPunishMoneyQueryEntity;

@MyBatisDao
public interface InspectPunishMoneyQueryDao extends CrudDao<InspectPunishMoneyQueryEntity> {

	
	public List<InspectPunishMoneyQueryEntity>   findName(Integer itemId);
	public List<InspectPunishMoneyQueryEntity> findListForExcel(InspectPunishMoneyQueryEntity entity);
}
