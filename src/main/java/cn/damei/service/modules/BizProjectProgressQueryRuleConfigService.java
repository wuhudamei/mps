package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizProjectProgressQueryRuleConfigDao;
import cn.damei.entity.modules.BizProjectProgressQueryRuleConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 大看板查询基础规则配置Service
 * Created by hyh on 2017/12/8.
 */
@Service
@Transactional(readOnly = true)
public class BizProjectProgressQueryRuleConfigService extends CrudService2<BizProjectProgressQueryRuleConfigDao, BizProjectProgressQueryRuleConfig> {

    /**
     * 根据父Id获取子集合
     * @param parentId
     * @return
     */
    public List<BizProjectProgressQueryRuleConfig> findChildList(Integer parentId){
        return dao.findChildList(parentId);
    }

    /**
     * 获取一级节点设置
     * @return
     */
    public List<BizProjectProgressQueryRuleConfig> findFirstList(){
        return dao.findFirstList();
    }

    /**
     * 删除节点
     * @param config
     */
    @Transactional(readOnly = false)
    public void  deleteConfig(BizProjectProgressQueryRuleConfig config){
        List<Integer> list = new ArrayList<Integer>();
        list.add(config.getId());
        recursionFindConfig(list,config);
        dao.deleteBatch(list);
    }

    /**
     * 递归获取节点下的所有子节点
     * @param config
     */
    public void recursionFindChildConfig(BizProjectProgressQueryRuleConfig config){
        List<BizProjectProgressQueryRuleConfig> childList = dao.findChildList(config.getId());
        for(BizProjectProgressQueryRuleConfig childConfig : childList){
            recursionFindChildConfig(childConfig);
        }
        config.setChildList(childList);
    }

    /**
     *递归获取节点下的所有子节点的Id
     */
    public void recursionFindConfig(List<Integer> list,BizProjectProgressQueryRuleConfig config){
        List<BizProjectProgressQueryRuleConfig> childList = dao.findChildList(config.getId());
        if(childList != null && childList.size() > 0){
            for (BizProjectProgressQueryRuleConfig childConfig : childList){
                recursionFindConfig(list,childConfig);
                list.add(childConfig.getId());
            }
        }
    }

    /**
     * 批量删除
     * @param list
     */
    @Transactional(readOnly = false)
    public void deleteBatch(List<Integer> list){
        dao.deleteBatch(list);
    }
}
