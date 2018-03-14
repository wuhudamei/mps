package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizProjectProgressQueryRuleConfigDao;
import cn.damei.entity.modules.BizProjectProgressQueryRuleConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class BizProjectProgressQueryRuleConfigService extends CrudService2<BizProjectProgressQueryRuleConfigDao, BizProjectProgressQueryRuleConfig> {


    public List<BizProjectProgressQueryRuleConfig> findChildList(Integer parentId){
        return dao.findChildList(parentId);
    }


    public List<BizProjectProgressQueryRuleConfig> findFirstList(){
        return dao.findFirstList();
    }


    @Transactional(readOnly = false)
    public void  deleteConfig(BizProjectProgressQueryRuleConfig config){
        List<Integer> list = new ArrayList<Integer>();
        list.add(config.getId());
        recursionFindConfig(list,config);
        dao.deleteBatch(list);
    }


    public void recursionFindChildConfig(BizProjectProgressQueryRuleConfig config){
        List<BizProjectProgressQueryRuleConfig> childList = dao.findChildList(config.getId());
        for(BizProjectProgressQueryRuleConfig childConfig : childList){
            recursionFindChildConfig(childConfig);
        }
        config.setChildList(childList);
    }


    public void recursionFindConfig(List<Integer> list,BizProjectProgressQueryRuleConfig config){
        List<BizProjectProgressQueryRuleConfig> childList = dao.findChildList(config.getId());
        if(childList != null && childList.size() > 0){
            for (BizProjectProgressQueryRuleConfig childConfig : childList){
                recursionFindConfig(list,childConfig);
                list.add(childConfig.getId());
            }
        }
    }


    @Transactional(readOnly = false)
    public void deleteBatch(List<Integer> list){
        dao.deleteBatch(list);
    }
}
