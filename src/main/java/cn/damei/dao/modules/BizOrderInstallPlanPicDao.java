package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallPlanPic;


@MyBatisDao
public interface BizOrderInstallPlanPicDao extends CrudDao2<BizOrderInstallPlanPic>{

	List<BizOrderInstallPlanPic> getByIdList(Integer orderInstallPlanId);

}
