package cn.damei.service.modules;

import cn.damei.dao.modules.DelaySheetStandBookDao;
import cn.damei.entity.modules.DelaySheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <dl>
 * <dd>描述:工程台账延期单台账Service</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/7</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@Service
public class DelaySheetStandBookService {
    @Autowired
    private DelaySheetStandBookDao delaySheetStandBookDao;

    /**
     * 根据订单编号查询工程台账延期单台账
     * @param orderno 订单编号
     */
    public List<DelaySheet> findDelaySheet(String orderno){
        try{
            List<DelaySheet> list = delaySheetStandBookDao.findDelaySheet(orderno);
            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
