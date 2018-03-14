package cn.damei.service.modules;

import cn.damei.dao.modules.ConstructionPlanStandBookDao;
import cn.damei.entity.modules.BizNodePlanExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <dl>
 * <dd>描述:施工计划台账Service</dd>
 * <dd>公司: 大城若谷信息技术有限公司</dd>
 * <dd>创建时间：2017/7/3</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@Service
public class ConstructionPlanStandBookService {
    @Autowired
    private ConstructionPlanStandBookDao constructionPlanStandBookDao;

    /**
     * 根据订单编号查询施工计划台账列表
     * @param orderno 订单编号
     */
    public List<BizNodePlanExtend> findPlanAndDoneTimeByOrderNo(String orderno){
        try{
            return constructionPlanStandBookDao.findPlanAndDoneTimeByOrderNo(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
