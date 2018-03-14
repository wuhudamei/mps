/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 工人组管理Service
 * 
 * @author qhy
 * @version 2016-09-01
 */
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
     /*   bizEmGroupRelationService.deleteEmgrouprelationByGroupId(bizEmployeegroup.getId());*/
        super.delete(bizEmployeegroup);
    }
    
    /**
     * 检查工人是否已经在某个工人组里了
     * @author chaowang
     * @date 2016年10月11日
     * @param bizemployeeId 员工id
     * @return
     */
	public int hasInGroup(String bizemployeeId)
	{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("bizemployeeId", bizemployeeId);
		return bizEmployeegroupDao.hasInGroup(param);
	}

	public BizEmployeegroup findBizEmployeegroup(String string) {
		// TODO Auto-generated method stub
		return bizEmployeegroupDao.findBizEmployeegroup(string);
	}
	 @Transactional(readOnly = false)
	public void updateStarLog(String groupid, Integer reason,
			String changeDescribe, Integer star) {
		bizEmployeegroupDao.updateStarLog(groupid,reason,changeDescribe,star);
		
	}
}