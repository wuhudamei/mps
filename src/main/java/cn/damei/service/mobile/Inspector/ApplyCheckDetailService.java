package cn.damei.service.mobile.Inspector;

import cn.damei.dao.mobile.Inspector.ApplyCheckDetailDao;
import cn.damei.entity.mobile.Inspector.ApplyCheckOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly=true)
public class ApplyCheckDetailService {

    @Autowired
    private ApplyCheckDetailDao dao;




   public List<ApplyCheckOrderEntity> findCheckDoneOrderListByManagerId(ApplyCheckOrderEntity orderEntity){


        return dao.findCheckDoneOrderListByManagerId(orderEntity);
    }


    public  ApplyCheckOrderEntity applyCheckDetailByOrderId(Integer orderId){

        return dao.applyCheckDetailByOrderId(orderId);
    }
    public  List<String> picList(Integer qcBillId){

        return dao.picList(qcBillId);
    }
}
