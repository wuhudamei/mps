package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.LaborCapital;

import java.util.List;

@MyBatisDao
public interface LaborCapitalDao extends CrudDao2<LaborCapital> {

    List<LaborCapital> exportQuery(LaborCapital laborCapital);
}
