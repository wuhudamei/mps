package cn.damei.entity.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.damei.common.persistence.DataEntity2;

import java.util.List;

/**
 * 大看板查询基础规则配置
 * Created by hyh on 2017/12/8.
 */
public class BizProjectProgressQueryRuleConfig extends DataEntity2<BizProjectProgressQueryRuleConfig> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    //父id
    private Integer parentId;

    //下标索引
    private Integer indexNo;

    //主sql内容
    private String mainSqlContent;

    //英文字段
    private String enColumnName;

    //中文字段
    private String cnColumnName;

    //是否特殊  0：否  1:是
    private Integer isSpecial;

    //分组标识内容
    private  String groupFlagContent;

    private List<BizProjectProgressQueryRuleConfig> childList;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getMainSqlContent() {
        return mainSqlContent;
    }

    public void setMainSqlContent(String mainSqlContent) {
        this.mainSqlContent = mainSqlContent;
    }

    public String getEnColumnName() {
        return enColumnName;
    }

    public void setEnColumnName(String enColumnName) {
        this.enColumnName = enColumnName;
    }

    public String getCnColumnName() {
        return cnColumnName;
    }

    public void setCnColumnName(String cnColumnName) {
        this.cnColumnName = cnColumnName;
    }

    public Integer getIsSpecial() {
        if (isSpecial == null) {
            isSpecial = 0;
        }
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getGroupFlagContent() {
        return groupFlagContent;
    }

    public void setGroupFlagContent(String groupFlagContent) {
        this.groupFlagContent = groupFlagContent;
    }

    public List<BizProjectProgressQueryRuleConfig> getChildList() {
        return childList;
    }

    public void setChildList(List<BizProjectProgressQueryRuleConfig> childList) {
        this.childList = childList;
    }

    @JsonIgnore
    public static void sortList(List<BizProjectProgressQueryRuleConfig> list, List<BizProjectProgressQueryRuleConfig> sourcelist, Integer parentId, boolean cascade) {
        for (int i = 0; i < sourcelist.size(); i++) {
            BizProjectProgressQueryRuleConfig e = sourcelist.get(i);
            if (parentId == null) {
                if (e.getParentId() == null) {
                    list.add(e);
                    if (cascade) {
                        // 判断是否还有子节点, 有则继续获取子节点
                        for (int j = 0; j < sourcelist.size(); j++) {
                            BizProjectProgressQueryRuleConfig child = sourcelist.get(j);
                            if (child.getParentId() != null
                                    && child.getParentId().equals(e.getId())) {
                                sortList(list, sourcelist, e.getId(), true);
                                break;
                            }
                        }
                    }
                }
            } else {
                if (e.getParentId() != null && e.getParentId().equals(parentId)) {
                    list.add(e);
                    if (cascade) {
                        // 判断是否还有子节点, 有则继续获取子节点
                        for (int j = 0; j < sourcelist.size(); j++) {
                            BizProjectProgressQueryRuleConfig child = sourcelist.get(j);
                            if (child.getParentId() != null
                                    && child.getParentId().equals(e.getId())) {
                                sortList(list, sourcelist, e.getId(), true);
                                break;
                            }
                        }
                    }
                }
            }

        }
    }
}
