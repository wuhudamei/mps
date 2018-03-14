package cn.damei.dao.modules;

import cn.damei.entity.modules.DelaySheet;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * <dl>
 * <dd>描述:工程台账延期单台账Dao</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/7</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@MyBatisDao
public interface DelaySheetStandBookDao {
    /**
     * 查询工程台账延期单
     */
    List<DelaySheet> findDelaySheet(String orderno);
}
