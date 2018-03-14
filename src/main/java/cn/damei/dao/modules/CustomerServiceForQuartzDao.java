package cn.damei.dao.modules;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;



@MyBatisDao
public interface CustomerServiceForQuartzDao {



     void saveCustomerServiceData(List<Map<String, Object>> list);



     void updateCustomerServiceData(List<Map<String, Object>> list);


     List<String>  customerServiceDataIsExist(List<String> list);

     void  update(Map<String,Object> map);
}
