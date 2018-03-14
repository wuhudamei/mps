package cn.damei.service.modules;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ToDoItemDao;
import cn.damei.entity.modules.ToDoItemEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * 待办事项service
 *
 * @author lzm
 * @version 2017-7-14
 */
@Service
public class ToDoItemService extends CrudService<ToDoItemDao, ToDoItemEntity> {
    //type:0表示要更新查看,1表示更新处理，2表示都更新

    @Transactional(readOnly = false)
    public void updateViewdOrSolvedByObj(String id, String type) {
        if (type != null && type.equals("0")) {
            dao.updateViewdByObj(id);
        } else if (type != null && type.equals("1")) {
            dao.updateSolvedByObj(id);
        } else if (type != null && type.equals("2")) {
            dao.updateViewedAndSolvedByObj(id);
        }
    }

    public String selectId(String relatedBusinessType, String relatedBusinessId, String orderId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("relatedBusinessType", relatedBusinessType);
        map.put("relatedBusinessId", relatedBusinessId);
        map.put("orderId", orderId);
        return dao.selectId(map);
    }


    /**
     * 根据订单 和type (qcNodeId)查询约检待办的数据
     *
     * @param map
     * @return
     */
    public Map<String, Object> getToDoInfoByMap(Map<String, String> map) {

        return dao.getToDoInfoByMap(map);


    }

    public String findNextIdByPreId(String qcNodeId) {

        return dao.findNextIdByPreId(qcNodeId);


    }

    @Transactional(readOnly = false)
    public void insertToDoInfo(ToDoItemEntity ToDoItemEntity) {

        dao.insert(ToDoItemEntity);
    }


    @Transactional(readOnly = false)
    public void updateToDoItemInfoByOrderAndManagerId(Integer orderId, Integer managerId) {

        dao.updateToDoItemInfoByOrderAndManagerId(orderId, managerId);

    }

	@Transactional(readOnly = false)
	public void saveDealUrlById(String id,String dealUrl){
		dao.saveDealUrlById(id, dealUrl);
	}
	@Transactional(readOnly = false)
	public void updateUrgePayStatusByItemId(String id,String status){
		dao.updateUrgePayStatusByItemId(id, status);
	}



	public int findUrgePay(String qcNodeId){


	    return dao.findIsUrgePay(qcNodeId);
    }
}
