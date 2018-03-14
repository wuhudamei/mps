package cn.damei.service.mobile.Inspector;

import cn.damei.dao.mobile.Inspector.ApplyCheckDetailDao;
import cn.damei.entity.mobile.Inspector.ApplyCheckOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joseph on 2017/4/22.
 */
@Service
@Transactional(readOnly=true)
public class ApplyCheckDetailService {

    @Autowired
    private ApplyCheckDetailDao dao;



    /**
     * 查询项目经理下的验收订单详情
     * @param orderEntity
     * @return
     */
   public List<ApplyCheckOrderEntity> findCheckDoneOrderListByManagerId(ApplyCheckOrderEntity orderEntity){


        return dao.findCheckDoneOrderListByManagerId(orderEntity);
    }

    /**
     * 根据订单Id查询详情
     * @param orderId
     * @return
     */
    public  ApplyCheckOrderEntity applyCheckDetailByOrderId(Integer orderId){

        return dao.applyCheckDetailByOrderId(orderId);
    }
    public  List<String> picList(Integer qcBillId){

        return dao.picList(qcBillId);
    }
}
