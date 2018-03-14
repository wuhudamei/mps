package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallPlanPic;

/**
 * 工程安装
 * @author llp
 * 2016/10/28
 */
@MyBatisDao
public interface BizOrderInstallPlanPicDao extends CrudDao2<BizOrderInstallPlanPic>{

	List<BizOrderInstallPlanPic> getByIdList(Integer orderInstallPlanId);

}
