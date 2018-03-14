package cn.damei.service.modules;

import cn.damei.dao.modules.QualityCheckDao;
import cn.damei.entity.modules.QualityCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dd>描述:质检台账Service</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/13</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@Service
public class QualityCheckService {

    @Autowired
    private QualityCheckDao qualityCheckDao;

    /**
     * 质检台账
     * @param orderno 订单号
     */
    public List<QualityCheck> findQualityCheck(String orderno){
        try{
            return this.qualityCheckDao.findQualityCheck(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    /**
     * 复检台账
     * @param orderno 订单号
     */
    public List<QualityCheck> findRepeatQualityCheck(String orderno){
        try{
            return this.qualityCheckDao.findRepeatQualityCheck(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

}
