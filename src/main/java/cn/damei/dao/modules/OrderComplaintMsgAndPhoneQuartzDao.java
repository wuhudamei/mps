package cn.damei.dao.modules;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/7/31.
 */
@MyBatisDao
public interface OrderComplaintMsgAndPhoneQuartzDao {


    List<Map<String,Object>> getAllDataForQuartz();
}
