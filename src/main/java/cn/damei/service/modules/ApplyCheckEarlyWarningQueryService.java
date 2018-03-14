package cn.damei.service.modules;

import cn.damei.common.service.CrudService;

import cn.damei.dao.modules.ApplyCheckEarlyWarningDetailDao;
import cn.damei.dao.modules.ApplyCheckEarlyWarningQueryDao;
import cn.damei.entity.modules.ApplyCheckEarlyWarningQueryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * Created by joseph on 2017/4/7.
 */
@Service
@Transactional(readOnly = false)
public class ApplyCheckEarlyWarningQueryService extends CrudService<ApplyCheckEarlyWarningQueryDao,ApplyCheckEarlyWarningQueryEntity>{

    @Autowired
    private ApplyCheckEarlyWarningDetailDao detailDao;

   public List<Integer> findDelayOrderId(){

        return dao.findDelayOrderId();
    }
    public   List<Integer> findAntiDelayOrderId(){

        return dao.findAntiDelayOrderId();
    }







}
