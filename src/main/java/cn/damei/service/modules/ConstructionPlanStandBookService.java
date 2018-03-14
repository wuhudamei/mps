package cn.damei.service.modules;

import cn.damei.dao.modules.ConstructionPlanStandBookDao;
import cn.damei.entity.modules.BizNodePlanExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class ConstructionPlanStandBookService {
    @Autowired
    private ConstructionPlanStandBookDao constructionPlanStandBookDao;


    public List<BizNodePlanExtend> findPlanAndDoneTimeByOrderNo(String orderno){
        try{
            return constructionPlanStandBookDao.findPlanAndDoneTimeByOrderNo(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
