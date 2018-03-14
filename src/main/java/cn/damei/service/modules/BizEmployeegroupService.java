
package cn.damei.service.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizEmployeegroupDao;
import cn.damei.entity.modules.BizEmployeegroup;


@Service
@Transactional(readOnly = true)
public class BizEmployeegroupService extends CrudService<BizEmployeegroupDao, BizEmployeegroup> {
	
	@Autowired
	BizEmployeegroupDao bizEmployeegroupDao;
	
    @Autowired
    private BizEmGroupRelationService bizEmGroupRelationService;
    
    public BizEmployeegroup get(String id) {
        return super.get(id);
    }

    public List<BizEmployeegroup> findList(BizEmployeegroup bizEmployeegroup) {
        return super.findList(bizEmployeegroup);
    }

    public Page<BizEmployeegroup> findPage(Page<BizEmployeegroup> page, BizEmployeegroup bizEmployeegroup) {
        return super.findPage(page, bizEmployeegroup);
    }

    @Transactional(readOnly = false)
    public void save(BizEmployeegroup bizEmployeegroup) {
        super.save(bizEmployeegroup);
    }

    @Transactional(readOnly = false)
    public void delete(BizEmployeegroup bizEmployeegroup) {
        System.out.println("BizEmployeegroupService delete BizEmployeegroup id:" + bizEmployeegroup.getId());

        super.delete(bizEmployeegroup);
    }
    

	public int hasInGroup(String bizemployeeId)
	{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("bizemployeeId", bizemployeeId);
		return bizEmployeegroupDao.hasInGroup(param);
	}

	public BizEmployeegroup findBizEmployeegroup(String string) {

		return bizEmployeegroupDao.findBizEmployeegroup(string);
	}
	 @Transactional(readOnly = false)
	public void updateStarLog(String groupid, Integer reason,
			String changeDescribe, Integer star) {
		bizEmployeegroupDao.updateStarLog(groupid,reason,changeDescribe,star);
		
	}
}