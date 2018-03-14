/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.CheckNodeDao;
import cn.damei.entity.modules.CheckNode;
import cn.damei.entity.modules.DropModel;

/**
 * 约检节点设置Service
 *
 * @author 梅浩
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class CheckNodeService extends CrudService2<CheckNodeDao, CheckNode> {

    public CheckNode get(Integer id) {
        return super.get(id);
    }

    public List<CheckNode> findList(CheckNode checkNode) {
        return super.findList(checkNode);
    }

    public Page<CheckNode> findPage(Page<CheckNode> page, CheckNode checkNode) {
        return super.findPage(page, checkNode);
    }

    @Transactional(readOnly = false)
    public void save(CheckNode checkNode) {
        super.save(checkNode);
    }

    @Transactional(readOnly = false)
    public void delete(CheckNode checkNode) {
        super.delete(checkNode);
    }


    /**
     * 根据门店id 动态查询节点
     *
     * @param node
     * @return
     */
    public List<CheckNode> findConsList(CheckNode node) {

        return dao.findConsList(node);
    }

    /**
     * 根据门店id查询门店下的约检节点
     *
     * @param map
     * @return
     */
    public List<DropModel> queryNodeListByStoreId(Map<String, Object> map) {

        return dao.queryNodeListByStoreId(map);
    }



    /**
     * 根据门店和模式  确认是否可以选择为基装节点
     * @param storeId  projectMode
     * @return
     */
    public String checkIsOkForBasicNode(String storeId, String projectMode) {


        return dao.checkIsOkForBasicNode(storeId, projectMode);


    }


    public 	Integer checkIsOkForDelete(Integer qcNodeId){

        return dao.checkIsOkForDelete(qcNodeId);
    }

}