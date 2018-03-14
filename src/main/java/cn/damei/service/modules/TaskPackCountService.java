package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.TaskPackCountDao;
import cn.damei.entity.modules.TaskPackCount;

@Service
@Transactional
public class TaskPackCountService extends CrudService<TaskPackCountDao, TaskPackCount>{

	public List<TaskPackCount> findCount(TaskPackCount taskPackCount) {

		return dao.findCount(taskPackCount);
	}

    @Override
    public Page<TaskPackCount> findPage(Page<TaskPackCount> page, TaskPackCount entity) {
        entity.setPage(page);

        List<TaskPackCount> list = dao.findOrderTaskpack(entity);
        List<String> ids = new ArrayList<>();
        for (TaskPackCount count: list) {
            ids.add(count.getId());
        }
        String joinString = StringUtils.join(ids,",");

        Map<String, Object> map = new HashMap<>();
        map.put("type",BusinessLogConstantUtil.BUSINESS_TYPE_1001);
        map.put("ids",ids);
        List<TaskPackCount> list20 = dao.findLogDatetime(map);
        map.put("type",BusinessLogConstantUtil.BUSINESS_TYPE_1201);
        List<TaskPackCount> list55 = dao.findLogDatetime(map);
        map.put("type",BusinessLogConstantUtil.BUSINESS_TYPE_1101);
        List<TaskPackCount> list60 = dao.findLogDatetime(map);

        Map<String,TaskPackCount> map20= listToMap(list20);
        Map<String,TaskPackCount> map55= listToMap(list55);
        Map<String,TaskPackCount> map60= listToMap(list60);
        for (TaskPackCount count: list) {
            if(map20.size()>0){
                TaskPackCount task = map20.get(count.getId());
                if(task != null){
                    count.setTaskPackStatus20(map20.get(count.getId()).getTaskPackStatus20());
                }
            }
            if(map55.size()>0){
                TaskPackCount task = map55.get(count.getId());
                if(task != null){
                    count.setTaskPackStatus55(map55.get(count.getId()).getTaskPackStatus20());
                }
            }
            if(map60.size()>0){
                TaskPackCount task = map60.get(count.getId());
                if(task != null){
                    count.setTaskPackStatus60(map60.get(count.getId()).getTaskPackStatus20());
                }
            }
        }
        page.setList(list);
        return page;
    }

    public Map<String,TaskPackCount> listToMap(List<TaskPackCount> list){
            Map<String,TaskPackCount> map = new HashMap<>();
            if(list.size()>0){
                for (TaskPackCount count:list) {
                    map.put(count.getId(),count);
                }
                return map;
            }else{
                return new HashMap<>();
            }
    }

}
