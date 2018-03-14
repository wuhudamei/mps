package cn.damei.dao.mobile.Manager;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EnginInstall;


@MyBatisDao
public interface EnginInstallDao extends CrudDao2<EnginInstall> {


	EnginInstall queryOrderDetails(Integer id);

}
