package cn.damei.dao.modules;

import cn.damei.entity.modules.PrincipalInstall;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * <dl>
 * <dd>描述:主材安装台账Dao</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/14</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@MyBatisDao
public interface PrincipalInstallDao {
    List<PrincipalInstall> findPrincipalInstall(String orderno);
}
