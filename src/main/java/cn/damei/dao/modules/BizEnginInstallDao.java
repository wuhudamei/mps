package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEnginInstall;

/**
 * 订单安装项
 * @author llp
 */
@MyBatisDao
public interface BizEnginInstallDao extends CrudDao2<BizEnginInstall>{

	List<BizEnginInstall> getByList();
	
}
