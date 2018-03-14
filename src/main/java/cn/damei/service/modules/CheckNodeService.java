
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



    public List<CheckNode> findConsList(CheckNode node) {

        return dao.findConsList(node);
    }


    public List<DropModel> queryNodeListByStoreId(Map<String, Object> map) {

        return dao.queryNodeListByStoreId(map);
    }




    public String checkIsOkForBasicNode(String storeId, String projectMode) {


        return dao.checkIsOkForBasicNode(storeId, projectMode);


    }


    public 	Integer checkIsOkForDelete(Integer qcNodeId){

        return dao.checkIsOkForDelete(qcNodeId);
    }

}