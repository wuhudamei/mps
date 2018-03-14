package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ExportEvalInfo;

/**
 * 评价分数导出Dao
 * Created by hyh on 2017/11/29.
 */
@MyBatisDao
public interface ExportEvalInfoDao  extends CrudDao2<ExportEvalInfo> {
}
