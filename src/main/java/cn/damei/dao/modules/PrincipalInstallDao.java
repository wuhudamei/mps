package cn.damei.dao.modules;

import cn.damei.entity.modules.PrincipalInstall;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;


@MyBatisDao
public interface PrincipalInstallDao {
    List<PrincipalInstall> findPrincipalInstall(String orderno);
}
