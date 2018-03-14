
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OaNotifyRecord;


@MyBatisDao
public interface OaNotifyRecordDao extends CrudDao<OaNotifyRecord> {


	public int insertAll(List<OaNotifyRecord> oaNotifyRecordList);
	

	public int deleteByOaNotifyId(String oaNotifyId);
	
}