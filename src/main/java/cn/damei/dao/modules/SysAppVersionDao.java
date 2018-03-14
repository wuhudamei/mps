/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.SysAppVersion;

import java.util.Map;

/**
 * 手机app版本DAO接口
 * @author qww
 * @version 2016-12-27
 */
@MyBatisDao
public interface SysAppVersionDao extends CrudDao2<SysAppVersion> {

    /**
     * 查询最大版本号
     * @param appType
     * @return
     */
    public String queryMaxVersion(String appType);

    /**
     * 查询版本个数
     * @param map
     * @return
     */
    public int queryCountVersion(Map<String, Object> map);
    
    /**
     * 接口
     * @param appType
     * @return
     */
    public SysAppVersion queryNewestVersion(String appType);
}