package cn.damei.dao.modules;
import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ApplyCheckEarlyWarningQueryEntity;

import java.util.List;



@MyBatisDao
public interface ApplyCheckEarlyWarningQueryDao  extends CrudDao<ApplyCheckEarlyWarningQueryEntity>{



     List<Integer> findDelayOrderId();
     List<Integer> findAntiDelayOrderId();



}
