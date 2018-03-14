package cn.damei.dao.modules;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface OrderComplaintMsgAndPhoneQuartzDao {


    List<Map<String,Object>> getAllDataForQuartz();
}
