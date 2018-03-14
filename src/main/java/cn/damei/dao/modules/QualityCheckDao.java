package cn.damei.dao.modules;

import cn.damei.entity.modules.QualityCheck;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * <dl>
 * <dd>描述:质检台账Dao</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/13</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@MyBatisDao
public interface QualityCheckDao {
    List<QualityCheck> findQualityCheck(String orderno);
    List<QualityCheck> findRepeatQualityCheck(String orderno);
}
