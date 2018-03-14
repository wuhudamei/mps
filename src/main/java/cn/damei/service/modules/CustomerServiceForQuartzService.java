package cn.damei.service.modules;

import cn.damei.dao.modules.CustomerServiceForQuartzDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/6/23.
 */
@Service
public class CustomerServiceForQuartzService {


    @Autowired
    private CustomerServiceForQuartzDao customerServiceForQuartzDao;


    /**
     * 保存售后问题
     *
     * @param mapList
     */
    @Transactional(readOnly = false)
    public void saveCustomerServiceData(List<Map<String, Object>> mapList) {

        customerServiceForQuartzDao.saveCustomerServiceData(mapList);
    }

    /**
     * 更新售后问题
     */
    @Transactional(readOnly = false)
    public void updateCustomerServiceData(List<Map<String, Object>> mapList) {
        customerServiceForQuartzDao.updateCustomerServiceData(mapList);

    }
    /**
     * 更新售后问题
     */
    @Transactional(readOnly = false)
    public  void  update(Map<String,Object>map){


        customerServiceForQuartzDao.update(map);
    }


    public List<String> customerServiceDataIsExist(List<String> dataList) {


        //根据code查询是否存在
        //用标识符 , code  的map映射 返回List
        return customerServiceForQuartzDao.customerServiceDataIsExist(dataList);

    }

}
