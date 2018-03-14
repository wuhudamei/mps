
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessPic;


@MyBatisDao
public interface BizBusinessPicDao extends CrudDao2<BizBusinessPic>{

	List<BizBusinessPic> getByBusinessId(Integer valueOf);

	List<BizBusinessPic> getByRecheckID(Integer recheckID, String type);

}