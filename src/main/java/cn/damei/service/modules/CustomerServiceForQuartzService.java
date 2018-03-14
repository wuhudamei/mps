package cn.damei.service.modules;

import cn.damei.dao.modules.CustomerServiceForQuartzDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class CustomerServiceForQuartzService {


    @Autowired
    private CustomerServiceForQuartzDao customerServiceForQuartzDao;



    @Transactional(readOnly = false)
    public void saveCustomerServiceData(List<Map<String, Object>> mapList) {

        customerServiceForQuartzDao.saveCustomerServiceData(mapList);
    }


    @Transactional(readOnly = false)
    public void updateCustomerServiceData(List<Map<String, Object>> mapList) {
        customerServiceForQuartzDao.updateCustomerServiceData(mapList);

    }

    @Transactional(readOnly = false)
    public  void  update(Map<String,Object>map){


        customerServiceForQuartzDao.update(map);
    }


    public List<String> customerServiceDataIsExist(List<String> dataList) {




        return customerServiceForQuartzDao.customerServiceDataIsExist(dataList);

    }

}
