package cn.damei.dao.modules;

import cn.damei.entity.modules.DelaySheet;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;


@MyBatisDao
public interface DelaySheetStandBookDao {

    List<DelaySheet> findDelaySheet(String orderno);
}
