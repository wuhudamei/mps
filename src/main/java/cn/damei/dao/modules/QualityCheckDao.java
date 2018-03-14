package cn.damei.dao.modules;

import cn.damei.entity.modules.QualityCheck;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;


@MyBatisDao
public interface QualityCheckDao {
    List<QualityCheck> findQualityCheck(String orderno);
    List<QualityCheck> findRepeatQualityCheck(String orderno);
}
