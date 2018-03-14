
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.SysAppVersion;

import java.util.Map;


@MyBatisDao
public interface SysAppVersionDao extends CrudDao2<SysAppVersion> {


    public String queryMaxVersion(String appType);


    public int queryCountVersion(Map<String, Object> map);
    

    public SysAppVersion queryNewestVersion(String appType);
}