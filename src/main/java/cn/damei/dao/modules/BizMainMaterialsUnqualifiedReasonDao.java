/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMainMaterialsUnqualifiedReason;
import cn.damei.entity.modules.BizProjectInstallItem;

import java.util.List;

/**
 * 主材安装项验收不合格原因配置DAO接口
 * @author wyb
 * @version 2018-01-23
 */
@MyBatisDao
public interface BizMainMaterialsUnqualifiedReasonDao extends CrudDao2<BizMainMaterialsUnqualifiedReason> {

    /**
     * 动态加载主材安装项
     * @param bizProjectInstallItem
     * @return
     */
    List<BizProjectInstallItem> queryInstallItemList(BizProjectInstallItem bizProjectInstallItem);

    /**
     * 停启用
     * @param bizMainMaterialsUnqualifiedReason
     * @return
     */
    boolean updateRreasonEnable(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean updateRreasonDelete(Integer id);

    /**
     * 根据安装项计划id查询安装项不合格验收原因列表
     * @param id
     * @return
     */
    List<BizMainMaterialsUnqualifiedReason> queryUnqualifiedReasonList(Integer id);

}